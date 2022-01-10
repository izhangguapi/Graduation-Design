// pages/password/password.js
//获取应用实例
const app = getApp();
 
 
Page({
 
  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    username:'',
    password:'',
    status:'stu'
  },
  formSubmit: function (e) {
    console.log(e);
    var oldpwd = e.detail.value.oldpwd;
    var newpwd = e.detail.value.newpwd;
    var newpwd2 = e.detail.value.newpwd2;
    var app = getApp();
    var that = this;
    let userInfo0=wx.getStorageSync('userInfo')
    if(userInfo0){
      this.setData({
        userInfo: JSON.parse(userInfo0)
      })
    }
    if (oldpwd == '' || newpwd == '' || newpwd2 == '') {
      wx.showToast({
        title: '密码不能为空',
        icon: 'none',
        duration: 1000
      })
    } else if (newpwd != newpwd2) {
      wx.showToast({
        title: '两次密码输入不一致',
        icon: 'none',
        duration: 1000
      })
    } else {
      wx.showLoading({
        title: '网络请求中......',
      })
      console.log(that.data.userInfo.data.id)
      console.log(that.data.userInfo.data.username)
      console.log(newpwd)
      console.log(that.data.status)
      wx.request({
        url: 'http://192.168.43.187:8080/users/update',
        method: 'POST',
        data: {
          id:that.data.userInfo.data.id,
          username:that.data.userInfo.data.username,
          password: newpwd,
          status:that.data.status
        },
        header: {
          "content-type": "application/json"
          // 'application/x-www-form-urlencoded'
        },
        success: (res) => {
          console.log(res.data);
          if (res.data.error) {
            wx.showToast({
              title: res.data.msg,
              icon: 'none',
              duration: 2000,
            })
          } else {
            wx.showToast({
              title: res.data.msg,
              icon: 'success',
              duration: 2000,
              success: function () {
                setTimeout(function () {
                  wx.switchTab({
                    url: '/pages/personal/personal',
                  })
                }, 2000)
              } 
            })
          }
        }
      })
    }
  },
 
        /**
         * 生命周期函数--监听页面加载
         */
        onLoad: function (options) {
 
        },
 
        /**
         * 生命周期函数--监听页面初次渲染完成
         */
        onReady: function () {
 
        },
 
        /**
         * 生命周期函数--监听页面显示
         */
        onShow: function () {
 
        },
 
        /**
         * 生命周期函数--监听页面隐藏
         */
        onHide: function () {
 
        },
 
        /**
         * 生命周期函数--监听页面卸载
         */
        onUnload: function () {
 
        },
 
        /**
         * 页面相关事件处理函数--监听用户下拉动作
         */
        onPullDownRefresh: function () {
 
        },
 
        /**
         * 页面上拉触底事件的处理函数
         */
        onReachBottom: function () {
 
        },
 
        /**
         * 用户点击右上角分享
         */
        onShareAppMessage: function () {
 
        }
})