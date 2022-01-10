/**
  作者: Created by zhiyongzaixian
  说明: 登录流程
  1. 收集表单项数据
  2. 前端验证
    1) 验证用户信息(账号，密码)是否合法
    2) 前端验证不通过就提示用户，不需要发请求给后端
    3) 前端验证通过了，发请求(携带账号, 密码)给服务器端
  3. 后端验证
    1) 验证用户是否存在
    2) 用户不存在直接返回，告诉前端用户不存在
    3) 用户存在需要验证密码是否正确
    4) 密码不正确返回给前端提示密码不正确
    5) 密码正确返回给前端数据，提示用户登录成功(会携带用户的相关信息)
*/
var app =getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
  
    username: '', // 手机号
    password: '' ,// 用户密码,

    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  // 表单项内容发生改变的回调
  handleInput(event){
    // let type = event.currentTarget.id;// id传值 取值： phone || password
    let type = event.currentTarget.dataset.type; // data-key=value
    // console.log(event);
    this.setData({
      [type]: event.detail.value
    })
  },
  
  
  // 登录的回调
  async login(){
    // 1. 收集表单项数据
    let {username, password} = this.data;
    // 2. 前端验证

    
    if(!username){
      // 提示用户
      wx.showToast({
        title: '账号不能为空',
        icon: 'none'
      })
      return;
    }
    
    if(!password){
      wx.showToast({
        title: '密码不能为空',
        icon: 'none'
      })
      return;
    }
  wx.request({
    url: 'http://192.168.43.187:8080/users/login',
    data:{
      username: this.data.username,
      password: this.data.password
    },
    method: "POST",
        header: {
          "content-type": "application/json"
        },
        success: function success(res) {
          console.log(res)
          if(res.data.code === 200){ // 登录成功
            wx.showToast({
              title: '登录成功'
            })
            //将用户的信息存储至本地
            wx.setStorageSync('userInfo', JSON.stringify(res.data)) 
            wx.setStorageSync('token', JSON.stringify(res.header.Authorization))   
            app.globalData.token=res.header.Authorization
            console.log(app.globalData.token)
            // 跳转至个人中心personal页面
            wx.reLaunch({
              url: '/pages/personal/personal'
            })
          }else if(res.data.code === 500){
            wx.showToast({
              title: '账号密码错误',
              icon: 'none'
            })
          }else if(res.data.code === 502){
            wx.showToast({
              title: '输入错误',
              icon: 'none'
            })
          }else {
            wx.showToast({
              title: '登录失败，请重新登录',
              icon: 'none'
            })
          }
  }
  })

    
    // let res = await request("/login", {username, password},'POST')
    // console.log(res)
    // if(res.code === 200){ // 登录成功
    //   wx.showToast({
    //     title: '登录成功'
    //   })
    //   // 将用户的信息存储至本地
    //   wx.setStorageSync('userInfo', JSON.stringify(res.data))
      
      
    //   // 跳转至个人中心personal页面
    //   wx.reLaunch({
    //     url: '/pages/personal/personal'
    //   })
    // }else if(res.code === 500){
    //   wx.showToast({
    //     title: '账号密码错误',
    //     icon: 'none'
    //   })
    // }else if(res.code === 502){
    //   wx.showToast({
    //     title: '输入错误',
    //     icon: 'none'
    //   })
    // }else {
    //   wx.showToast({
    //     title: '登录失败，请重新登录',
    //     icon: 'none'
    //   })
    // }
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
