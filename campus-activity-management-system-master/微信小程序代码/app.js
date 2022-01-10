

App({

  /**
   * @type：{function}
   * @explain：完成程序初始化后触发一次
   */
  onLaunch: function () {
    this.globalData.usrBaseInfo = '123'
  },

  /**
   * @type：{function}
   * @explain：用户基础信息获取
   */
  getUsrInfo: function (cb) {
    var that = this
    if (this.globalData.usrDesInfo) {
      typeof cb == "function" && cb(this.globalData.usrBaseInfo)
    } else {
      /*wx.redirectTo({
        url: '登录页面',
      })*/
      wx.login({
        success: function () {
          wx.getUserInfo({
            success: function (res) {
              that.globalData.usrBaseInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.usrBaseInfo)
            }
          })
        }
      })
    }
  },

  /**
   * @type：{function}
   * @explain：用户加密信息获取
   */
  getUsrDes: function (cb) {
    var that = this
    if (this.globalData.usrDesInfo) {
      typeof cb == "function" && cb(this.globalData.usrBaseInfo)
    } else {
      wx.redirectTo({
        url: 'String',
      })
    }
  },

  /**
   * @type：{object}
   * @explain：全局参数
   */
  globalData: {
    usrBaseInfo: null,
    usrDesInfo: null,
    token:''
  }

})