
import _conf from '../../mars/conf/config'

Page({
  data: {
    
      imgUrls: [
        '/static/images/swiper1.jpeg',
        '/static/images/swiper2.jpeg',
        '/static/images/swiper3.jpeg'
      ],
      indicatorDots: true,  //是否显示面板指示点
      autoplay: true,      //是否自动切换
      interval: 3000,       //自动切换时间间隔
      duration: 1000,       //滑动动画时长
      inputShowed: false,
      inputVal: "",
      catelogimg:'/logo.png'
  },
  onLoad: function (options) {
    // 生命周期函数--监听页面加载
    
  },
  topicture(){
    wx.navigateTo({
      url: '/pages/activity/activity',
    })
  },
  onReady: function () {
    // 生命周期函数--监听页面初次渲染完成

  },
  onShareAppMessage: function () {
    // 用户点击右上角分享
    return {
      title: _conf.share_info.title, // 分享标题
      desc: _conf.share_info.desc, // 分享描述
      path: _conf.share_info.path // 分享路径
    }
  },

})

