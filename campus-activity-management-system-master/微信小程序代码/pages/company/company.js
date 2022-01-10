// pages/enroll/enroll.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    status:'stu',
    username: "",
    password: "",
    isname:false,
    ispass:false
  },

    /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  signin: function (e) {
    wx.redirectTo({//这里进行页面跳转功能
      url: '/pages/login/login'
    })
  },
  handleInput(e){
  
     this.setData({
        password: e.detail.value
     })
    //  let type = event.currentTarget.dataset.type; // data-key=value
    //  console.log(type,event.detail.value);
  },
  handle1Input(e){
  
    this.setData({
       username: e.detail.value
    })
   //  let type = event.currentTarget.dataset.type; // data-key=value
   //  console.log(type,event.detail.value);
 },

  
   
  regist: function () {
    // let {name, password} = this.data;
    var that = this;
    var username = this.data.username;
    var password = this.data.password;
    // console.log(name)
    // console.log(password)
    if (!username) {
      wx.showModal({
        title: "错误",
        content: "用户名不能为空"
      });      
      that.isname = false;
    } else {
      that.isname = true;
    }
    if (!password) {
      wx.showModal({
        title: "错误",
        content: "密码不能为空"
      });      
      that.ispass = false;
    } else {
      that.ispass = true;
    }
    if (that.ispass && that.isname) {
      // 提交
      wx.request({
        url: "http://192.168.43.187:8080/users/register", //接口地址
        data: {
          status:'stu',
          username: that.data.username,
          password: that.data.password,
        },
        method: "POST",
        header: {
          "content-type": "application/json"
        },
        success: function success(res) {
          //页面跳转
          wx.navigateTo({
            url: '/pages/login/login'
          });
          //页面跳转
        }
      });
      // 提交
    }


  
    
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
