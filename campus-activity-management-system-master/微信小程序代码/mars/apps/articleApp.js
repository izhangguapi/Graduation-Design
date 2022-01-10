import baseFun from '../common/base'

(function (exports) {
	/**
	 *@explai：新闻列表
	 */
    exports.getArtList = function (params, callback) {
        params.url = "/A/getArtList" //统一使用配置文件中的请求头部
        baseFun.WxRequest(params, callback)
    }
	
} (module.exports))