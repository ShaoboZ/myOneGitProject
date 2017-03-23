package cn.com.bo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


public abstract class BaseController {

	/**
	 * ��־����
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * �������·��
	 */
	@Value("${adminPath}")
	protected String adminPath;
	
	/**
	 * ǰ�˻���·��
	 */
	@Value("${frontPath}")
	protected String frontPath;
	
	/**
	 * ǰ��URL��׺
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;
}
