package cn.cinema.manage.iface;

public interface IClientService {

	/**
	 * 获取软件版本
	 * @param appcode 应用编码
	 * @param validatekey 加密key
	 * @return
	 */
	String getVersion(String appcode,String validatekey);
	
	/**
	 * 影片详细信息
	 * @param appcode 应用编码
	 * @param validatekey 加密key
	 * @param filmno 影片编码
	 * @return
	 */
	String getFilmInfo(String appcode,String validatekey, String filmno);
	
	/**
	 * @deprecated
	 * @param appcode 应用编码
	 * @param validatekey 加密key
	 * @param pageNo 页码
	 * @param pageSize 页显示条数
	 * @return
	 */
	String getFilmList(String appcode,String validatekey, int pageNo, int pageSize);
	
	/**
	 * 获取热映影片列表
	 * @param appcode 应用编码
	 * @param validatekey 加密key
	 * @param filmtype 类型 1,热映,2即将上映
	 * @param cityno 城市编码
	 * @param placeno 影院编码
	 * @return
	 */
	public String getHotFilmList(String appcode, String validatekey, String filmtype, String cityno ,String placeno);
	
	/**
	 * 根据影片编码影院编码获取排期
	 * @param appcode
	 * @param validatekey
	 * @param filmno 影片编码
	 * @param placeno 影院编码
	 * @param featuredate 排期日期
	 * @return
	 */
	public String getFilmFeature(String appcode, String validatekey, String filmno, String cinemano, String featuredate);
	
	/**
	 * 通过影院获取某天的排期
	 * @param appcode
	 * @param validatekey
	 * @param cinemano == placeno  影院编码
	 * @param featuredate 排期日期
	 * @return
	 */
	public String getFeatureByCinema(String appcode, String validatekey, String cinemano, String featuredate);
	
	/**
	 * 根据排期获取座位图
	 * @param appcode 
	 * @param validatekey
	 * @param featureappno 排期应用编码
	 * @return
	 */
	public String getPlanSiteState(String appcode, String validatekey, String featureappno);
	
	/**
	 * 预算接口
	 * @param appcode
	 * @param validatekey
	 * @param userId 用户id
	 * @param placeNo 影院编号
	 * @param partnerId 合作商流水号
	 * @param cardId 会员卡卡号或账户号，账户号前加$
	 * @param mobilePhone 手机号
	 * @param passWord 会员卡密码
	 * @param oldPrice 预算：原始价格=排期价格乘以票数，支付：原始价格=预算价格乘以票数，折扣传10
	 * @param tracePrice 交易费用，即交易手续费，无手续费传0
	 * @param discount 折扣，范围：0~10，0：影院规则，1-10：1折-10折
	 * @param featureNo 排期号
	 * @param filmNo 影片编号
	 * @param ticketNum 票数，预算传1
	 * @param traceMemo 备注
	 * @return
	 */
	public String goBudget(String appcode, String validatekey, String userId,
			String placeNo, String partnerId, String cardId, String mobilePhone, String passWord,
			String oldPrice, String tracePrice, String discount, String featureNo, 
			String filmNo, String ticketNum, String traceMemo);
	
	/**
	 * 锁座
	 * @param appcode
	 * @param validatekey
	 * @param userId 用户id
	 * @param featureappno  排期应用号
	 * @param serialNum 流水号
	 * @param recvmobileno  接收二唯码手机号码
	 * @param seatinfo 座位信息，json格式
	 * @param deductMoney 预算后的单张票价
	 * @return
	 */
	public String RealCheck(String appcode, String validatekey, String userId, String featureappno, String recvmobileno, String seatinfo, String deductMoney);
	
	/**
	 * 扣费
	 * @param appcode
	 * @param validatekey
	 * @param userId 用户id
	 * @param orderNo 订单号
	 * @param placeNo 影院编号
	 * @param partnerId 合作商流水号
	 * @param cardId 会员卡卡号或账户号，账户号前加$
	 * @param mobilePhone 手机号
	 * @param passWord 会员卡密码
	 * @param oldPrice 预算：原始价格=排期价格乘以票数，支付：原始价格=预算价格乘以票数，折扣传10
	 * @param tracePrice 交易费用，即交易手续费，无手续费传0
	 * @param discount 折扣，范围：0~10，0：影院规则，1-10：1折-10折
	 * @param featureNo 排期号
	 * @param filmNo 影片编号
	 * @param ticketNum 票数，预算传1
	 * @param traceMemo 备注
	 * @return
	 */
	public String cardPay(String appcode, String validatekey, String userId,String orderNo,
			String placeNo, String partnerId, String cardId, String mobilePhone, String passWord,
			String oldPrice, String tracePrice, String discount, String featureNo, 
			String filmNo, String ticketNum, String traceMemo);
	
	/**
	 * 会员注册
	 * @param appcode
	 * @param validatekey
	 * @param userName 用户名
	 * @param passWord 密码 加密过的
	 * @param mobilePhone 手机号
	 * @return
	 */
	public String userRegister(String appcode, String validatekey, String userName, String passWord, String mobilePhone);
	
	/**
	 * 会员登录
	 * @param appcode
	 * @param validatekey
	 * @param mobilePhone 手机号
	 * @param passWord 密码
	 * @return
	 */
	public String userLogin(String appcode, String validatekey, String mobilePhone, String passWord);
	
	/**
	 * 获取订单列表
	 * @param appcode
	 * @param validatekey
	 * @param userId 用户id
	 * @param orderNo 获取订单详细内容使用，如获取列表忽略此参数（订单列表不返回详细内容参数）
	 * @return
	 */
	public String getOrderList(String appcode, String validatekey, String userId, String orderNo);
}
