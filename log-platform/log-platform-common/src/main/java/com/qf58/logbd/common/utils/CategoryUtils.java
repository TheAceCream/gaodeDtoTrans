package com.qf58.bd.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * Description: 品类处理
 * User: weishenpeng
 * Date: 2017/9/7
 * Time: 下午 04:20
 */
public class CategoryUtils {

	public static final String SPLIT = ",";

	/**
	 * 获取品类ID的List<Long>
	 *
	 * @param cateStr
	 * @return
	 */
	public static List<Long> getCateIdsByString(String cateStr) {
		List<Long> cateIdList = new ArrayList<>();
		if (StringUtils.isNotEmpty(cateStr)) {
			cateStr = cateStr.startsWith(SPLIT) ? cateStr.substring(1) : cateStr;
			String[] cateStrIds = cateStr.split(SPLIT);
			if (cateStrIds != null && cateStrIds.length > 0) {
				for (String cateStrId : cateStrIds) {
					if (StringUtils.isNotEmpty(cateStrId)) {
						try {
							cateIdList.add(Long.parseLong(cateStrId));
						}catch (Exception e) {
							System.out.println("品类错误");
						}
					}
				}
			}
		}
		return cateIdList;
	}

	/**
	 * 去重合并品类ID
	 *
	 * @param cateIds1
	 * @param cateIds2
	 * @return
	 */
	public static String unionCateIds(String cateIds1, String cateIds2) {
		String resultCateIds = "";
		if (StringUtils.isEmpty(cateIds1)) {
			resultCateIds = cateIds2;
		} else if (StringUtils.isEmpty(cateIds2)) {
			resultCateIds = cateIds1;
		} else {
			String[] cateIdArray1 = StringUtils.split(cateIds1, SPLIT);
			String[] cateIdArray2 = StringUtils.split(cateIds2, SPLIT);
			Set<String> set = new HashSet<String>();
			Collections.addAll(set, cateIdArray1);
			Collections.addAll(set, cateIdArray2);
			StringBuffer resultCateIdsSb = new StringBuffer();
			for (String str : set) {
				if (StringUtils.isNotEmpty(str)) {
					resultCateIdsSb.append(str).append(SPLIT);
				}
			}
			resultCateIds = resultCateIdsSb.toString();
		}

		if (StringUtils.isNotEmpty(resultCateIds)) {
			if (!resultCateIds.startsWith(SPLIT)) {
				resultCateIds = SPLIT + resultCateIds;
			}
			if (!resultCateIds.endsWith(SPLIT)) {
				resultCateIds += SPLIT;
			}
		}
		return resultCateIds;
	}

	/**
	 * 品类ids 是否仅包含指定品类
	 *
	 * @param categoryList
	 * @param category
	 * @return
	 */
	public static boolean isOnlyContainTheCate(String categoryList, String category) {
		String[] cateArray = categoryList.split(",");
		boolean flag = true;
		for (String cate : cateArray) {
			if (!StringUtils.isEmpty(cate) && !category.equals(cate)) {
				flag = false;
				break;
			}
		}
		return flag;
	}


	/**
	 * 品类排序
	 *
	 * @param cateStr
	 * @param ifAsc
	 * @return
	 */
	public static String cateLikeStrSort(String cateStr, boolean ifAsc) {
		if (StringUtils.isNotEmpty(cateStr)) {
			List<Long> cateIdList = getCateIdsByString(cateStr);
			cateIdList.sort(new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					return o1 > o2 ? 1 : o1 < o2 ? -1 : 0;
				}
			});
			StringBuffer sb = new StringBuffer();
			cateIdList.forEach(new Consumer<Long>() {
				@Override
				public void accept(Long aLong) {
					sb.append(aLong).append(",%");
				}
			});
			if (StringUtils.isNotEmpty(sb)) {
				sb.insert(0, "%,");
			}
			return sb.toString();
		}
		return cateStr;
	}

	public static void main(String[] args) {

		String str1 = ",2,3,4,";
		String str2 = ",2,3,67,";
		System.out.println(unionCateIds(str1, str2));
	}

}
