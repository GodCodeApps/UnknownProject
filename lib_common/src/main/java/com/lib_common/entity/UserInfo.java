package com.lib_common.entity;

import java.io.Serializable;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/28  16:49
 * @description
 */
public class UserInfo implements Serializable{
     private int id;//用户ID
     private String nickname;//	昵称
     private String imageurl;//头像完整路径": "http://oss.diandian7.com/ddq-test/12.jpg",
     private String birthday;//": "1988-05-17",
     private int gender;//性别：1-男，2-女，3-保密": 1,
     private int role;//角色:1-普通用户，2-已认证用户
     private String province;//省
     private String city;//市
     private String intro;//个人简介
     private int bindaccount;//绑定账户类型（位）：1-手机，2-微信
     private int aid;//正在认证ID(未审核前)
     private int age;//年龄
     private String imtoken;
     private int followSum;//	我关注的用户数量
     private int fansSum;//	粉丝数量
     private int friendsSum;//	好友数量
     private int isrelation;//	关注状态1-已关注。2-相互关注，0-无关注
     private int balance;//钱包金额(单位分)
     private int orderstatus;//	接单状态：1-开启，2-关闭

     private int assets;//是否资产变动提醒，1-有变动，0-无变动
     private int coupons;//	是否优惠券变动提醒，1-有变动，0-无变动

     public void setAssets(int assets) {
          this.assets = assets;
     }

     public void setCoupons(int coupons) {
          this.coupons = coupons;
     }

     public int getAssets() {
          return assets;
     }

     public int getCoupons() {
          return coupons;
     }

     public int getOrderstatus() {
          return orderstatus;
     }

     public void setOrderstatus(int orderstatus) {
          this.orderstatus = orderstatus;
     }

     public int getBalance() {
          return balance;
     }

     public void setBalance(int balance) {
          this.balance = balance;
     }

     public int getIsrelation() {
          return isrelation;
     }

     public void setIsrelation(int isrelation) {
          this.isrelation = isrelation;
     }



     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getNickname() {
          return nickname;
     }

     public void setNickname(String nickname) {
          this.nickname = nickname;
     }

     public String getImageurl() {
          return imageurl;
     }

     public void setImageurl(String imageurl) {
          this.imageurl = imageurl;
     }

     public String getBirthday() {
          return birthday;
     }

     public void setBirthday(String birthday) {
          this.birthday = birthday;
     }

     public int getGender() {
          return gender;
     }

     public void setGender(int gender) {
          this.gender = gender;
     }

     public int getRole() {
          return role;
     }

     public void setRole(int role) {
          this.role = role;
     }

     public String getProvince() {
          return province;
     }

     public void setProvince(String province) {
          this.province = province;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String city) {
          this.city = city;
     }

     public String getIntro() {
          return intro;
     }

     public void setIntro(String intro) {
          this.intro = intro;
     }

     public int getBindaccount() {
          return bindaccount;
     }

     public void setBindaccount(int bindaccount) {
          this.bindaccount = bindaccount;
     }

     public int getAid() {
          return aid;
     }

     public void setAid(int aid) {
          this.aid = aid;
     }

     public int getAge() {
          return age;
     }

     public void setAge(int sex) {
          this.age = age;
     }

     public String getImtoken() {
          return imtoken;
     }

     public void setImtoken(String imtoken) {
          this.imtoken = imtoken;
     }

     public int getFollowSum() {
          return followSum;
     }

     public void setFollowSum(int followSum) {
          this.followSum = followSum;
     }

     public int getFansSum() {
          return fansSum;
     }

     public void setFansSum(int fansSum) {
          this.fansSum = fansSum;
     }

     public int getFriendsSum() {
          return friendsSum;
     }

     public void setFriendsSum(int friendsSum) {
          this.friendsSum = friendsSum;
     }
}
