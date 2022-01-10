// pages/registration/resgistration.js
Page({
  data: {
    goodsList: [
  
    ],
  },

  /**
   * 判断是点击了哪个活动，然后从全局变量里取出这个活动的信息
   * 然后从数据库查找这个用户是否报名了这个活动
   */
  btns3:function (event){
    var app = getApp();
    var that = this;
    let userInfo0=wx.getStorageSync('userInfo')
    if(userInfo0){
      this.setData({
        userInfo: JSON.parse(userInfo0)
      })
    }
    let aid0=wx.getStorageSync('aid')
    if(aid0){
      this.setData({
        aid: JSON.parse(aid0)
      })
    }
    console.log(that.data.aid)
    var ul = "http://192.168.43.187:8080/activities/delete?aid=" + that.data.aid + "&uid= "+ that.data.userInfo.data.id;
    console.log(ul)
    wx.request({
      url: ul,
      data:{
      },
      method: "DELETE",
          header: {
            "content-type": "application/json",
          },
        
    success: function success(res) {
      console.log(res.data)
      if(res.data.code === 200){ // 删除成功
        wx.showToast({
          title: '删除成功'
        })
        wx.reLaunch({
          url: '/pages/lanuch/lanuch',
        })
        
}
}

    })
  },
  onLoad: function () {
    var app = getApp();
    var that = this;
    var getData
    let userInfo0=wx.getStorageSync('userInfo')
    if(userInfo0){
      this.setData({
        userInfo: JSON.parse(userInfo0)
      })
    }
    console.log(that.data.userInfo.data.id)
    let aid0=wx.getStorageSync('aid')
    if(aid0){
      this.setData({
        aid: JSON.parse(aid0)
      })
    }
    console.log(that.data.aid)
    var ul = "http://192.168.43.187:8080/activities/detail?aid=" 
    + that.data.aid + "&uid= "+ that.data.userInfo.data.id;
    console.log(ul)
    wx.request({
      url: ul,
      data:{
      },
      method: "GET",
          header: {
            "content-type": "application/json"
          },
        
    success: function success(res) {
      console.log(res.data)
      getData = res.data
      that.setData({
        goodsList:getData.data,
      })
      console.log(that.data.goodsList)
}

    })
  },
/*
*点击报名或已报名后触发,如果是报名就添加数据进数据库，如果是已报名就从数据库删除对应数据
*/
  issubmit(){
    var ul = null;
    var userInfo = wx.getStorageSync("userinfo")
    if(this.data.issu == 0){
      ul = "http://127.0.0.1:8808/insert?name=" + userInfo.nickName + "&id=" + this.data.item.id;
      this.setData({
        issu: 1
      })
    }else{
      ul = "http://127.0.0.1:8808/delete?name=" + userInfo.nickName + "&id=" + this.data.item.id;
      this.setData({
        issu: 0
      })
    }
    wx.request({
      url: ul, 
      header: { 'content-type': 'application/json' },
      success: res => {
      }
    })
  }
})
