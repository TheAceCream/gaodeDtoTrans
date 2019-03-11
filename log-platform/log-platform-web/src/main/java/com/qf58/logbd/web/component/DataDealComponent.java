package com.qf58.bd.web.component;

import com.bj58.qifu.dto.CateDto;
import com.bj58.qifu.service.CateService;
import com.qf58.bd.common.utils.NumberUtils;
import com.qf58.bd.web.pub.WebPubCommon;
import com.qf58.crm.enums.*;
import com.qifu.sso.user.agent.UserAgent;
import com.qifu.sso.user.dto.AclUserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Description:
 *
 * @Author: weishenpeng
 * Date: 2018/1/10
 * Time: 上午 10:47
 */
@Component("dataDealComponent")
public class DataDealComponent {

	@Resource
	private CateService cateService;
	@Resource
	private UserAgent userAgent;

	/**
	 * KeyValue 空值
	 */
	private static final String NULL_VALUE = "NULL";

	/**
	 * 跟据keyValue 获取展示 key名字
	 *
	 * @param keyWord
	 * @param keyVal
	 * @return
	 */
	public String getNewKeyName(String keyWord, String keyVal) {

		String newKeyName = "";
		if ("city_id".equals(keyWord) || "city".equals(keyWord)) {
			if (StringUtils.isNotEmpty(keyVal) && !NULL_VALUE.equals(keyVal)) {
				CityEnum cityEnum = CityEnum.getByCode(Long.valueOf(keyVal));
				newKeyName = cityEnum != null ? cityEnum.getDesc() : "";
			}
		} else if ("source_canal&source_type".equals(keyWord)) {
			String[] sources = keyVal.split("&");
			if (sources.length > 0 && StringUtils.isNotEmpty(sources[0]) && !NULL_VALUE.equals(sources[0])) {
				SourceCanalEnum sourceCanalEnum = SourceCanalEnum.getByCode(Integer.valueOf(sources[0]));
				newKeyName = sourceCanalEnum != null ? sourceCanalEnum.getDesc() : "";
			}
			if (sources.length >= 2 && StringUtils.isNotEmpty(sources[1]) && !NULL_VALUE.equals(sources[1])) {
				SourceTypeEnum sourceTypeEnum = SourceTypeEnum.getByCode(Integer.valueOf(sources[1]));
				newKeyName += sourceTypeEnum != null ? ("/" + sourceTypeEnum.getDesc()) : "";
			}
		} else if ("source_terminal".equals(keyWord)) {
			if (StringUtils.isNotEmpty(keyVal) && !NULL_VALUE.equals(keyVal)) {
				SourceTerminalEnum sourceTerminalEnum = SourceTerminalEnum.getCode(Integer.valueOf(keyVal));
				newKeyName = sourceTerminalEnum != null ? sourceTerminalEnum.getDesc() : "";
			}
		} else if ("submit_type".equals(keyWord)) {
			String[] sources = keyVal.split("&");
			if (sources.length > 0 && StringUtils.isNotEmpty(sources[0]) && !NULL_VALUE.equals(sources[0])) {
				ClueSubmitTypeEnum submitTypeEnum = ClueSubmitTypeEnum.getByCode(Integer.valueOf(sources[0]));
				newKeyName = submitTypeEnum != null ? submitTypeEnum.getDesc() : "";
			}
		} else if ("category".equals(keyWord)) {
			String[] sources = keyVal.split("&");
			if (sources.length > 0 && StringUtils.isNotEmpty(sources[0]) && !NULL_VALUE.equals(sources[0])) {
				CateDto cateDto = cateService.getCateById(Long.valueOf(sources[0]));
				newKeyName = cateDto != null ? cateDto.getName() : "";
			}
		} else if ("industry".equals(keyWord)) {
			String[] sources = keyVal.split("&");
			if (sources.length > 0 && StringUtils.isNotEmpty(sources[0]) && !NULL_VALUE.equals(sources[0])) {
				CusIndustryEnum cusIndustryEnum = CusIndustryEnum.getByCode(Integer.valueOf(sources[0]));
				newKeyName = cusIndustryEnum != null ? cusIndustryEnum.getDesc() : "";
			}
		} else if("sale_id".equals(keyWord)) {
			String[] sources = keyVal.split("&");
			if (sources.length > 0 && StringUtils.isNotEmpty(sources[0]) && !NULL_VALUE.equals(sources[0])) {
				AclUserDto userDto = userAgent.findUserById(NumberUtils.stringToLong(sources[0]));
				newKeyName = userDto != null ? userDto.getRealName() : "";
			}
		} else if("project_type".equals(keyWord)) {
			String[] sources = keyVal.split("&");
			if (sources.length > 0 && StringUtils.isNotEmpty(sources[0]) && !NULL_VALUE.equals(sources[0])) {
				ProjectTypeEnum projectTypeEnum = ProjectTypeEnum.getCode(NumberUtils.stringToInteger(sources[0]));
				newKeyName = projectTypeEnum != null ? projectTypeEnum.getDesc() : "";
			}
		} else if("grade".equals(keyWord) || "customer_grade".equals(keyWord)) {
			String[] sources = keyVal.split("&");
			if (sources.length > 0 && StringUtils.isNotEmpty(sources[0]) && !NULL_VALUE.equals(sources[0])) {
				CusGradeEnum cusGradeEnum = CusGradeEnum.getByCode(NumberUtils.stringToInteger(sources[0]));
				newKeyName = cusGradeEnum != null ? cusGradeEnum.getDesc() : "";
			}
		}

		if (StringUtils.isEmpty(newKeyName)) {
//			newKeyName = WebPubCommon.UNKNOWN;
			return null;
		}
		return newKeyName;
	}

	/**
	 * 格式化处理keyword
	 *
	 * @param word
	 * @return
	 */
	public String getDealKeyWord(String word) {
		String[] wdArray = word.split("&");
		StringBuilder stringBuilder = new StringBuilder();
		Arrays.sort(wdArray);
		for (int i = 0; i < wdArray.length; i++) {
			if (i != 0) {
				stringBuilder.append("&");
			}
			stringBuilder.append(wdArray[i]);
		}
		return stringBuilder.toString();
	}

	/**
	 * 获取处理过得key_value
	 * @param keyWord
	 * @param keyValue
	 * @return
	 */
	public String getDealKeyValue(String keyWord, String keyValue) {
		int keyCount = 0;
		int valCount = 0;

		if (StringUtils.isNotEmpty(keyWord) && StringUtils.isNotEmpty(keyValue)) {
			char[] keyWordArray = keyWord.toCharArray();
			for (int i = 0; i < keyWordArray.length; i++) {
				if(keyWordArray[i] == '&') {
					keyCount++;
				}
			}
			char[] keyValueArray = keyValue.toCharArray();
			for (int i = 0; i < keyValueArray.length; i++) {
				if(keyValueArray[i] == '&') {
					valCount++;
				}
			}
			for(int i=valCount; i<keyCount; i++) {
				keyValue += "&NULL";
			}
		}
		return keyValue;
	}

}
