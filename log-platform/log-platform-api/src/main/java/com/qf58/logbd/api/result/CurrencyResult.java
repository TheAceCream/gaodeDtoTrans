package com.qf58.bd.api.result;

import com.qf58.bd.common.utils.Pager;

import java.io.Serializable;

/**
 * Description:
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 * @Author: weishenpeng  Date: 2017/12/12 Time: 下午 03:19
 */
public class CurrencyResult<K, T> implements Serializable {

	private K input;

	private T output;

	private Pager pager;

	/**
	 * Gets input.
	 *
	 * @return the input
	 */
	public K getInput() {
		return input;
	}

	/**
	 * Sets input.
	 *
	 * @param input the input
	 */
	public void setInput(K input) {
		this.input = input;
	}

	/**
	 * Gets output.
	 *
	 * @return the output
	 */
	public T getOutput() {
		return output;
	}

	/**
	 * Sets output.
	 *
	 * @param output the output
	 */
	public void setOutput(T output) {
		this.output = output;
	}

	/**
	 * Gets pager.
	 *
	 * @return the pager
	 */
	public Pager getPager() {
		return pager;
	}

	/**
	 * Sets pager.
	 *
	 * @param pager the pager
	 */
	public void setPager(Pager pager) {
		this.pager = pager;
	}


}
