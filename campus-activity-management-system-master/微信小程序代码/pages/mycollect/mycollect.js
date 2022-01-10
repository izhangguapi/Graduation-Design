// pages/takeLessons/lessonList.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
   
    goodsList: [],
    tab_type: "tack",
    top_text: "我的收藏",
    height: "",
    searchList: [],
  },

  // 上拉刷新
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading();
    wx.startPullDownRefresh();
    setTimeout(function () {

      // complete
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    }, 1500);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var app = getApp();
    var that = this;
    var getData
    wx.request({
      url: 'http://192.168.43.187:8080/activities/allactivities',
      data:{
      },
      method: "GET",
          header: {
            "content-type": "application/json"
          },
    //       success: function success(res) {
    //         console.log(res.data)
    //          wx.setStorageSync('getData', JSON.stringify(res.data)) 
    //         that.setData({
    //           goodsList:that.getData.data
    //         })
    //         console.log(that.data.goodsList)
    // }
    success: function success(res) {
      console.log(res.data)
      getData = res.data
      that.setData({
        goodsList:getData.data,
      })
}
    })
    
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    //获得lessonInfo组件
    this.lessonInfo = this.selectComponent("#lessonInfo");
    this.lessonDetail = this.selectComponent("#lessonDetail");
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
   * 页面上拉触底事件的处理函数
   */
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  // 点击抢课
  _stackLesson(event) {
    var index = event.detail.index;
    this.data.lesson_list[index].state = 2;
    this.data.my_list.push(this.data.lesson_list[index]);
    this.data.lesson_list.splice(index, 1);
    this.setData({
      lesson_list: this.data.lesson_list,
      my_list: this.data.my_list
    });
    this.lessonDetail.hide();
  },
  // 残忍拒绝
  _reject(event){
    var index = event.detail.index;
    this.data.lesson_list.splice(index, 1);
    this.setData({
      lesson_list: this.data.lesson_list,
      my_list: this.data.my_list
    });
    this.lessonDetail.hide();
  },
  // 查看课程详情
  _openDetail(event) {
    this.lessonDetail.show();
    this.lessonDetail.refreshData(event.detail);
  },
  // 切换tab
  switch(event) {
    var type = event.currentTarget.dataset.tabType;
    if (type != this.data.tab_type) {
      if (type == "tack") {
        this.setData({
          tab_type: "tack",
          top_text: "您的收藏",
        });
      } else {
        this.setData({
          tab_type: "my",
          top_text: "您的收藏",
        });
      };
    };
  },

  refreshData: function () {
    if (this.data.tab_type == "tack") {
      this.setData({
        lesson_list: this.data.tack_list
      });
    } else {
      this.setData({
        lesson_list: this.data.my_list
      });
    }
  }
})