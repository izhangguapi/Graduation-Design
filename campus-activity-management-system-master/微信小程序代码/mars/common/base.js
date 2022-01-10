
import config from '../../hiway/conf/config'
import Promise from '../plugins/bluebird'

(function (exports) {

    /**
 * @explain：执行微信请求并返回回调函数
 */
    exports.WxRequest = function (params, callback) {
        if (!params.method) params.method = "POST" //此处设置默认请求方式POST
        wx.request({
            url: config.domain + params.url, //请求地址
            data: Object.assign({}, params.data),
            header: {
                'content-type': 'application/json'
            },
            method: params.method,
            success: function (res) {
                if (res.statusCode == 200) {
                    callback(res.data)
                } else {
                    console.log('Request "' + params.url + '" failed')
                }
            }
        })
    }
	
	/**
 * @explain：记录日志
 * @param {Mixed} 记录的信息
 * @returns {Void}
 */
function log(msg) {
  if (!msg) return;
  if (getApp().settings['debug'])
    console.log(msg);
  let logs = wx.getStorageSync('logs') || [];
  logs.unshift(msg)
  wx.setStorageSync('logs', logs)
}

/**
 * @param {Function} func 接口
 * @param {Object} options 接口参数
 * @returns {Promise} Promise对象
*/
function promiseHandle(func, options) {
  options = options || {};
  return new Promise((resolve, reject) => {
    if (typeof func !== 'function')
        reject();
    options.success = resolve;
    options.fail = reject;
    func(options);
  });
}

} (module.exports))