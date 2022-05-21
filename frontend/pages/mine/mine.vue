<template>
	<view>
		<view class="top">
			<image src="../../static/mine/user.png"></image>
			<label>146****8006</label>
			<label>账号管理</label>
			<image src="../../static/icons/antOutline-right-circle.svg"></image>
		</view>
		<view class="nav">
			<view class="nav_item" v-for="(item, index) in navs" :key="index" @click="navItemClick(item.path)">
				<view>
					<image :src="item.icon"></image>
				</view>
				<text>{{item.title}}</text>
			</view>
		</view>
		<view class="label1"><text>个人信息/Personal Imformation</text></view>
		<view class="label1"><text>在线服务/Online Service</text></view>
		<view class="label1"><text>帮助与反馈/Help and Feedback</text></view>
		<view class="exit">退出登录</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				navs: [{
						icon :'/static/icons/antFill-bulb.svg',
						title: '全部订单',
						path: '/pages/order/order?index=0'
					},
					{
						icon :'/static/icons/fas fa-info-circle.svg',
						title: '待付款',
						path: '/pages/order/order?index=1'
					},
					{
						icon :'/static/icons/mb-question-filled.svg',
						title: '待提货',
						path: '/pages/order/order?index=2'
					},
					{
						icon :'/static/icons/antOutline-customer-service Copy.svg',
						title: '已提货',
						path: '/pages/order/order?index=3'
					}
				]
			}
		},
		methods: {
			navItemClick (url) {
				// console.log(url)
				uni.navigateTo({
					url
				})
			},
		}
	}
</script>

<style lang="scss">
	.top {
		width: 376px;
		height: 103px;
		line-height: 20px;
		background-color: rgba(186, 231, 255, 100);
		text-align: center;
		image:nth-child(1) {
			position: absolute;
			left: 23px;
			top: 1px;
			width: 55px;
			height: 55px;
			border: 2px solid rgba(117, 181, 215, 0);
		}
		label:nth-child(2) {
			position: absolute;
			left: 90px;
			top: 11px;
			width: 97px;
			height: 22px;
			color: rgba(255, 255, 255, 100);
			font-size: 16px;
			text-align: left;
			font-family: PingFangSC-regular;
		}
		label:nth-child(3) {
			position: absolute;
			left: 285px;
			top: 13px;
			width: 48px;
			height: 17px;
			color: rgba(255, 255, 255, 100);
			font-size: 12px;
			text-align: left;
			font-family: PingFangSC-regular;
		}
		image:nth-child(4) {
			position: absolute;
			left: 341px;
			top: 13px;
			width: 18px;
			height: 18px;
		}
	}
	.nav {
		display: flex;
		width: 376px;
		height: 123px;
		line-height: 20px;
		border-radius: 9px 12px 12px 12px;
		background-color: rgba(255, 255, 255, 100);
		color: rgba(255, 255, 255, 100);
		font-size: 14px;
		text-align: center;
		box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.12);
		font-family: Arial;
		.nav_item {
			width: 25%;
			text-align: center;
			
			view {
				width: 48px;
				height: 48px;
				line-height: 120rpx;
				border-radius: 35px;
				background-color: rgba(251, 251, 251, 100);
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: center;
				box-shadow: 0px 2px 6px 0px rgba(16, 16, 16, 0.22);
				font-family: Arial;
				border: 1px solid rgba(117, 181, 215, 100);
				margin: 20px 20px auto;
				image {
					width: 24px;
					height: 24px;
				}		
			}

			text {
				width: 56px;
				height: 20px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: left;
				font-family: PingFangSC-regular;
			}
		}
		margin-bottom: 60rpx;
	}
	.label1 {
		width: 375px;
		height: 50px;
		color: rgba(16, 16, 16, 100);
		border: 1px solid rgba(187, 187, 187, 100);
		
		
		text {
			line-height: 100rpx;
			margin-left: 34rpx;
			width: 202px;
			height: 20px;
			color: rgba(16, 16, 16, 100);
			font-size: 14px;
			text-align: left;
			font-family: PingFangSC-regular;
		}
	}
	
	.exit {
		margin-left: 86rpx;
		margin-top: 60rpx;
		width: 289px;
		height: 40px;
		border-radius: 4px;
		color: rgba(16, 16, 16, 100);
		font-size: 14px;
		text-align: center;
		font-family: Microsoft Yahei;
		border: 1px solid rgba(187, 187, 187, 100);
		line-height: 75rpx;
	}
</style>
