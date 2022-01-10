import request from "../../utils/request";


Page({

  /**
   * 页面的初始数据
   */
  data: {
    coverTransform: 'translateY(0)',
    coveTransition: '',
    userInfo: {}, // 用户信息
    recentPlayList: [], // 用户播放记录
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 读取用户的基本信息
    let userInfo = wx.getStorageSync('userInfo');
    if(userInfo){ // 用户登录
      // 更新userInfo的状态
      this.setData({
        userInfo: JSON.parse(userInfo)  //反编译
      })
    }
  },

  
  handleTouchStart(event){
    this.setData({
      coveTransition: ''
    })
    // 获取手指起始坐标
    startY = event.touches[0].clientY;
  },
  handleTouchMove(event){
    moveY = event.touches[0].clientY;
    moveDistance = moveY - startY;
    
    if(moveDistance <= 0){
      return;
    }
    if(moveDistance >= 80){
      moveDistance = 80;
    }
    // 动态更新coverTransform的状态值
    this.setData({
      coverTransform: `translateY(${moveDistance}rpx)`
    })
  },
  handleTouchEnd(){
    // 动态更新coverTransform的状态值
    this.setData({
      coverTransform: `translateY(0rpx)`,
      coveTransition: 'transform 1s linear'
    })
  },
  
  // 跳转至登录login页面的回调
  toLogin(){
    wx.navigateTo({
      url: '/pages/login/login'
    })
  },
  toLanuch()
  {
    wx.navigateTo({
      url: '/pages/lanuch/lanuch'
    })
  },
 toMyjoin()
 {
   wx.navigateTo({
     url: '/pages/myjoin/myjoin'
   })
 },
 toMycollect()
 {
   wx.navigateTo({
      url: '/pages/mycollect/mycollect'
   })
  },
toremove()
{
  wx.navigateTo({
    url:'/pages/remove/remove'
  })
},
 toMore()
 {
   wx.navigateTo({
     url: '/pages/more/more'
   })
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
