//获取应用实例
import request from '../../utils/request'
var app = getApp()
var Bmob = require("../../utils/bmob.js");
// var common = require('../template/getCode.js')
var that;
var myDate = new Date();
//格式化日期
function formate_data(myDate) {
  let month_add = myDate.getMonth() + 1;
  var formate_result = myDate.getFullYear() + '-'
    + month_add + '-'
    + myDate.getDate()
  return formate_result;
}
Page({
  /**
   * 页面的初始数据
   */
  data: {
    title:'',
    notice_status: false,
    accounts: ["微信号", "QQ号", "手机号"],
    accountIndex: 0,
    peopleHide: false,
    isAgree: false,
    date: formate_data(myDate),
    address: '点击选择位置',
    longitude: 0, //经度
    latitude: 0,//纬度
    showTopTips: false,
    TopTips: '',
    noteMaxLen: 200,//备注最多字数
    content: "",
    noteNowLen: 0,//备注当前字数
    types: ["线上活动", "线下活动"],
    // , "交友", "旅行", "读书", "竞赛", "电影", "音乐", "其他"
    typeIndex: "0",
    showInput: false,//显示输入真实姓名,
    // realname:''
  },

  tapNotice: function (e) {
    if (e.target.id == 'notice') {
      this.hideNotice();
    }
  },
  showNotice: function (e) {
    this.setData({
      'notice_status': true
    });
  },
  hideNotice: function (e) {
    this.setData({
      'notice_status': false
    });
  },
//活动名称
bindTextactname: function (e) {
  var that = this
  var value = e.detail.value,
    len = parseInt(value.length);
  if (len > that.data.noteMaxLen)
    return;
  that.setData({
    title: value
  })
},

  //字数改变触发事件
  bindTextAreaChange: function (e) {
    var that = this
    var value = e.detail.value,
      len = parseInt(value.length);
    if (len > that.data.noteMaxLen)
      return;
    that.setData({
      content: value, noteNowLen: len
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    that = this;
    that.setData({//初始化数据
      src: "",
      isSrc: false,
      ishide: "0",
      autoFocus: true,
      isLoading: false,
      loading: true,
      isdisabled: false
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.hideToast()
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var myInterval = setInterval(getReturn, 500); ////半秒定时查询
    function getReturn() {
      wx.getStorage({
        key: 'user_openid',
        success: function (ress) {
          if (ress.data) {
            clearInterval(myInterval)
            that.setData({
              loading: true
            })
          }
        }
      })
    }
  },

  //上传活动图片
  uploadPic: function () {//选择图标
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['compressed'], //压缩图
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        that.setData({
          isSrc: true,
          src: tempFilePaths
        })
      }
    })
  },

  //删除图片
  clearPic: function () {//删除图片
    that.setData({
      isSrc: false,
      src: ""
    })
  },

  //上传活动群二维码
  uploadCodePic: function () {//选择图标
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['compressed'],//压缩图
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        that.setData({
          isCodeSrc: true,
          codeSrc: tempFilePaths
        })
      }
    })
  },

  //删除活动群二维码
  clearCodePic: function () {
    that.setData({
      isCodeSrc: false,
      codeSrc: ""
    })
  },

  //限制人数
  switch1Change: function (e) {
    if (e.detail.value == false) {
      this.setData({
        peopleHide: false
      })
    } else if (e.detail.value == true) {
      this.setData({
        peopleHide: true
      })
    }
  },

  //改变时间
  bindDateChange: function (e) {
    this.setData({
      date: e.detail.value
    })
  },
  //改变活动类别
  bindTypeChange: function (e) {
    this.setData({
      typeIndex: e.detail.value
    })
  },
  //选择地点
  addressChange: function (e) {
    this.addressChoose(e);
  },
  addressChoose: function (e) {
    var that = this;
    wx.chooseLocation({
      success: function (res) {
        that.setData({
          address: res.name,
          longitude: res.longitude, //经度
          latitude: res.latitude,//纬度
        })
        if (e.detail && e.detail.value) {
          this.data.address = e.detail.value;
        }
      },
      fail: function (e) {
      },
      complete: function (e) {
      }
    })
  },

  //改变联系方式
  bindAccountChange: function (e) {
    this.setData({
      accountIndex: e.detail.value
    })
  },

  //同意相关条例
  bindAgreeChange: function (e) {
    this.setData({
      isAgree: !!e.detail.value.length,
      showInput: !this.data.showInput
    });
  },
// launch: function(){
//   var app = getApp();
//     var that = this;
//     var getData
//     let userInfo0=wx.getStorageSync('userInfo')
//     if(userInfo0){
//       this.setData({
//         userInfo: JSON.parse(userInfo0)
//       })
//     }
//     wx.request({
//       url: 'http://192.168.43.187:8080/activities/newactivity',
//       data:{
//         aname:this.data.title,
//         atime:this.data.date,
//         uusername:that.data.userInfo.data.username,
//         aadd:this.data.address,
//         acontent:this.data.content,
//         asort:this.data.types[this.data.typeIndex],
//         uid:that.data.userInfo.data.id,
//         },
     
//       method: "POST",
//           header: {
//             "content-type": "application/json"
//             // "application/x-www-form-unlencoded"
//           },
   
//     success: function success(res) {
//       console.log(res.data)
//       if(res.data.code === 200){ // 登录成功
//         wx.showToast({
//           title: '登录成功'
//         })
//         wx.reLaunch({
//           url: '/pages/personal/personal',
//         })
// }
//     }
//     })
// },
  //表单验证
  showTopTips: function () {
    var that = this;
    this.setData({
      showTopTips: true
    });
    setTimeout(function () {
      that.setData({
        showTopTips: false
      });
    }, 3000);
  },
  //提交表单
  launch: function () {
    var that = this;
   
    if (that.data.showInput == false) {
      wx.showModal({
        title: '提示',
        content: '请先阅读《发起须知》'
      })
      return;
    }
    if (that.data.title == "") {
      this.setData({
        showTopTips: true,
        TopTips: '请输入主题'
      });
    } else if (that.data.address == '点击选择位置') {
      this.setData({
        showTopTips: true,
        TopTips: '请选择地点'
      });
    } else if (that.data.content == "") {
      this.setData({
        showTopTips: true,
        TopTips: '请输入活动内容'
      });
    // }else if (realname == "") {
    //   this.setData({
    //     showTopTips: true,
    //     TopTips: '请输入真实姓名'
    //   });
    // } else if (realname != "" && !nameReg.test(realname)) {
    //   this.setData({
    //     showTopTips: true,
    //     TopTips: '真实姓名一般为2-4位汉字'
    //   });
    // } else if (contactValue == "") {
    //   this.setData({
    //     showTopTips: true,
    //     TopTips: '请输入联系方式'
    //   });
    // } else if (contactWay == "微信号" && !wxReg.test(contactValue)) {
    //   this.setData({
    //     showTopTips: true,
    //     TopTips: '微信号格式不正确'
    //   });
    // } else if (contactWay == "手机号" && !phReg.test(contactValue)) {
    //   this.setData({
    //     showTopTips: true,
    //     TopTips: '手机号格式不正确'
    //   });
    // } else if (contactWay == "QQ号" && !qqReg.test(contactValue)) {
    //   this.setData({
    //     showTopTips: true,
    //     TopTips: 'QQ号格式不正确'
    //   });
    } 
    else {
      console.log('校验完毕');
      that.setData({
        isLoading: true,
        isdisabled: true
      })
      //向 Events 表中新增一条数据
      var app = getApp();
      var that = this;
      var getData
      let userInfo0=wx.getStorageSync('userInfo')
      if(userInfo0){
        this.setData({
          userInfo: JSON.parse(userInfo0)
        })
      }
      wx.request({
        url: 'http://192.168.43.187:8080/activities/newactivity',
        data:{
          aname:this.data.title,
          atime:this.data.date,
          uusername:that.data.userInfo.data.username,
          aadd:this.data.address,
          acontent:this.data.content,
          asort:this.data.types[this.data.typeIndex],
          uid:that.data.userInfo.data.id,
          },
       
        method: "POST",
            header: {
              "content-type": "application/json"
              // "application/x-www-form-unlencoded"
            },
     
      success: function success(res) {
        console.log(res.data)
        if(res.data.code === 200){ // 登录成功
          wx.showToast({
            title: '发起成功'
          })
          wx.reLaunch({
            url: '/pages/personal/personal',
          })
  }
      }
      })
  }
    setTimeout(function () {
      that.setData({
        showTopTips: false
      });
    }, 1000);
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

  },
  onLoad: function () {
    // this.QueryParams.cid = options.cid;
    
    
    
  },
  
})

//根据活动类型获取活动类型名称
function getTypeName(acttype) {
  var acttypeName = "";
  if (acttype == 1) acttypeName = "online";
  else if (acttype == 2) acttypeName = "offline";
  // else if (acttype == 3) acttypeName = "交友";
  // else if (acttype == 4) acttypeName = "旅行";
  // else if (acttype == 5) acttypeName = "读书";
  // else if (acttype == 6) acttypeName = "竞赛";
  // else if (acttype == 7) acttypeName = "电影";
  // else if (acttype == 8) acttypeName = "音乐";
  // else if (acttype == 9) acttypeName = "其他";
  return acttypeName;
}