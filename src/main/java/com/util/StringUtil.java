package com.util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串帮助类
 */
public class StringUtil {

	// 城市信息
//	public static String beiJing[] = new String[] { "北京市", "东城区", "西城区", "朝阳区",
//			"丰台区", "石景山区", "海淀区", "门头沟区", "房山区", "通州区", "顺义区", "昌平区", "大兴区",
//			"怀柔区", "密云县", "延庆县" };
//	public static String shangHai[] = new String[] { "上海市", "黄浦区", "卢湾区",
//			"徐汇区", "长宁区", "静安区", "普陀区", "闸北区", "虹口区", "杨浦区", "闵行区", "宝山区",
//			"嘉定区", "浦东新区", "金山区", "松江区", "青浦区", "南汇区", "奉贤区", "崇明县" };
//	public static String chongQing[] = new String[] { "重庆市", "万州区", "涪陵区",
//			"渝中区", "大渡口区", "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "万盛区", "双桥区",
//			"渝北区", "巴南区", "黔江区", "长寿区", "綦江区", "潼南县", "璧山县", "铜梁县", "大足县",
//			"荣昌县", "梁平县", "城口县", "丰都县", "垫江县", "武隆县", "忠县", "开县", "云阳县", "奉节县",
//			"巫山县", "江津市", "巫溪县", "合川市", "永川市", "南川市", "石柱土家族自治县", "秀山土家族苗族自治县",
//			"酉阳土家族苗族自治县", "彭水苗族土家族自治县" };
//	public static String tianJin[] = new String[] { "天津市", "和平区", "河东区", "河西区",
//			"南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区", "东丽区", "西青区", "津南区",
//			"北辰区", "武清区", "宝坻区", "宁河县", "静海县", "蓟县" };
//
//	public static String beiJingCity = "东城区,西城区,朝阳区,丰台区,石景山区,海淀区,门头沟区,房山区,通州区,顺义区,昌平区,大兴区,怀柔区,密云县,延庆县,";
//	public static String shangHaiCity = "黄浦区,卢湾区,徐汇区,长宁区,静安区,普陀区,闸北区,虹口区,杨浦区,闵行区,宝山区,嘉定区,浦东新区,金山区,松江区,青浦区,南汇区,奉贤区,崇明县,";
//	public static String chongQingCity = "万州区,涪陵区,渝中区,大渡口区,江北区,沙坪坝区,九龙坡区,南岸区,北碚区,万盛区,双桥区,渝北区,巴南区,黔江区,长寿区,綦江区,潼南县,璧山县,铜梁县,大足县,荣昌县,梁平县,城口县,丰都县,垫江县,武隆县,忠县,开县,云阳县,奉节县,巫山县,江津市,巫溪县,合川市,永川市,南川市,石柱土家族自治县,秀山土家族苗族自治县,酉阳土家族苗族自治县,彭水苗族土家族自治县,";
//	public static String tianJinCity = "和平区,河东区,河西区,南开区,河北区,红桥区,塘沽区,汉沽区,大港区,东丽区,西青区,津南区,北辰区,武清区,宝坻区,宁河县,静海县,蓟县,";
//
//	public static String[] allProvince = new String[] { "", "北京市", "天津市",
//			"河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "上海市", "江苏省", "浙江省",
//			"安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区",
//			"海南省", "重庆市", "四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省",
//			"宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区", "台湾省" };

	/**
	 * 填充位置枚举类型
	 * 
	 * @author Administrator
	 *
	 */
	public enum FillLocation {
		BEFORE, AFTER
	}

	private static final String DEFAULT_PATH_SEPARATOR = ",";

	/**
	 * 将一个中间带逗号分隔符的字符串转换为ArrayList对象
	 * 
	 * @param str
	 *            待转换的符串对象
	 * @return List对象
	 */
	public static List<String> strToArrayList(String str) {
		return strToArrayListManager(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串对象按给定的分隔符separator转象为ArrayList对象
	 * 
	 * @param str
	 *            待转换的字符串对象
	 * @param separator
	 *            字符型分隔符
	 * @return List对象
	 */
	public static List<String> strToArrayList(String str, String separator) {
		return strToArrayListManager(str, separator);
	}

	private static List<String> strToArrayListManager(String str,
			String separator) {
		StringTokenizer strTokens = new StringTokenizer(str, separator);
		List<String> list = new ArrayList<String>();
		while (strTokens.hasMoreTokens()) {
			list.add(strTokens.nextToken().trim());
		}
		return list;
	}

	/**
	 * 将一个中间带逗号分隔符的字符串转换为字符型数组对象
	 * 
	 * @param str
	 *            待转换的符串对象
	 * @return 字符型数组
	 */
	public static String[] strToStrArray(String str) {
		return strToStrArrayManager(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串对象按给定的分隔符separator转象为字符型数组对象
	 * 
	 * @param str
	 *            待转换的符串对象
	 * @param separator
	 *            字符型分隔符
	 * @return 字符型数组
	 */
	public static String[] strToStrArray(String str, String separator) {
		return strToStrArrayManager(str, separator);
	}

	private static String[] strToStrArrayManager(String str, String separator) {
		StringTokenizer strTokens = new StringTokenizer(str, separator);
		String[] strArray = new String[strTokens.countTokens()];
		int i = 0;
		while (strTokens.hasMoreTokens()) {
			strArray[i] = strTokens.nextToken().trim();
			i++;
		}
		return strArray;
	}

	/**
	 * 将字符串转为set集合，使用,分割
	 * 
	 * @param str
	 * @return
	 */
	public static Set<String> strToSet(String str) {
		return strToSet(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串转为set集合，
	 * 
	 * @param str
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static Set<String> strToSet(String str, String separator) {
		String[] values = strToStrArray(str, separator);
		Set<String> result = new LinkedHashSet<String>();
		for (int i = 0; i < values.length; i++) {
			result.add(values[i]);
		}
		return result;
	}

	/**
	 * 将一个数组，用逗号分隔变为一个字符串
	 * 
	 * @param array
	 * @return
	 */
	public static String arrayToStr(Object[] array) {
		if (array == null || array.length < 0) {
			return null;
		}
		String str = "";
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				str += ",";
			}
			str += array[i].toString();
			i++;
		}
		return str;
	}

	/**
	 * 使用指定字符串填充已有字符串
	 * 
	 * @param inStr
	 *            待填充字符串
	 * @param fill
	 *            填充字符串
	 * @param length
	 *            填充后的长度
	 * @param location
	 *            填充方向
	 * @return
	 */
	public static String fillStr(String inStr, String fill, int length,
			FillLocation location) {
		if (inStr == null || inStr.length() > length || inStr.length() == 0) {
			return inStr;
		}
		StringBuffer tempStr = new StringBuffer("");
		for (int i = 0; i < length - inStr.length(); i++) {
			tempStr.append(fill);
		}
		if (location == FillLocation.BEFORE) {
			return new String(tempStr.toString() + inStr);
		} else {
			return new String(inStr + tempStr.toString());
		}
	}

	public static String replaceAll(String str, String src, String dest) {

		if (str == null || src == null || dest == null || str.equals("")
				|| src.equals("")) {
			return str;
		}
		int lensrc = src.length();
		int idx = str.indexOf(src);
		while (idx >= 0) {
			str = str.substring(0, idx) + dest + str.substring(idx + lensrc);
			idx = str.indexOf(src);
		}
		return str;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("") || str.trim().equals("null");
	}

	public static boolean isEmpty(String string, boolean ignoreWhiteSpace) {
		if (string == null)
			return true;
		return ignoreWhiteSpace ? string.trim().equals("") : string.equals("");
	}

	public static String ArrayToString(String[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			sb.append(",");
		}

		String result = sb.substring(0, sb.length() - 1);
		return result;
	}

//	public static String getProvince(Integer province) {
//		return allProvince[province];
//	}

	public static String listToString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			if (list.size() == 1) {
				sb.append(list.get(0));
			} else {
				for (String str : list) {
					sb.append(str + ",");
				}
				return sb.subSequence(0, sb.length() - 1).toString();
			}
		}
		return sb.toString();
	}

	public static List<String> stringToList(String source, String separaer) {
		List<String> list = new ArrayList<String>();
		if (source != null && source.length() > 0) {
			if (source.contains(separaer)) {
				String[] array = source.split(separaer);
				list.addAll(Arrays.asList(array));
			} else {
				list.add(source);
			}
		}
		return list;
	}

	public static String removeLastCharacter(String str, String flag) {
		if (str == null || flag == null)
			return null;

		if (!str.endsWith(flag)) {
			return str;
		} else {
			return str.substring(0, str.length() - 1);
		}
	}



	/**
	 * 解码
	 *
	 * @return String
	 * @author lifq
	 * @date 2015-3-17 下午04:09:51
	 */
	public static String getDecoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * URL 转码
	 *
	 * @return String
	 * @author lifq
	 * @date 2015-3-17 下午04:10:28
	 */
	public static String getEncoderString(String str) {
		String result = "";
		if (null == str) {
			return "";
		}
		try {
			result = java.net.URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 判断是否含有特殊字符
	 *
	 * @param str
	 * @return true为包含，false为不包含
	 */
	public static boolean isSpecialChar(String str) {
		if(StringUtil.isEmpty(str)){
			return true;
		}
		String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}



}
