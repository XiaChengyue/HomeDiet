<template>
	<view>
		<uni-search-bar></uni-search-bar>
		<view class="header">
			<view class="tabbar">
				<view @click="tabClick(index)" v-for="(tabName, index) in tabNames" :key="index"
					:class="index == activeIndex ? 'activeTab' : ''">
					<text>{{tabName}}</text>
					<view></view>
				</view>
			</view>
		</view>
		<view class="cards-style">
			<view v-for="(order, index) in orders" :key="index"
				v-if="activeIndex==0 || order.state==activeIndex" class="card-style">
				<view class="top">
					<text class="label1">{{order.time}}</text>
					<text class="label2">{{tabNames[order.state]}}</text>
				</view>
				<view class="stripe1"></view>
				<view v-for="(myorder, index) in order.myorders" :key="index" class="middle">
					<view class="order">
						<image :src="myorder.item.pic"></image>
						<text class="title">{{myorder.item.name}}</text>
						<text class="description">{{myorder.item.des}}</text>
						<text class="number">x{{myorder.num}}</text>
						<text class="price">￥{{myorder.item.price}}</text>
					</view>
				</view>
				<view class="stripe2"></view>
				<view class="down">
					<text class="number"> 应付总额： </text>
					<text class="cost">￥{{order.totalCost}}</text>
				</view>
				<view :class="order.state==1 ? 'payNow' : 'buyAgain'" @click="createOrder(index)">
					{{order.state== 1 ? '立即付款' : '再次购买'}}
				</view>
				<view class="blank"></view>
			</view>
		</view>
		<!-- <view class="bottom-bar">
			<view class="line"></view>
		</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				activeIndex: 0,
				tabNames: [
					"全部",
					"待付款",
					"待提货",
					"已提货"
				],
				orders: [
				]
			}
		},
		methods: {
			async createOrder(index) {
				// console.log(this.orders[index].myorders)
				var myorders = this.orders[index].myorders
				var tmporders = []
				for(let i = 0; i < myorders.length; i++) {
					var tmporder = {
						itemid: myorders[i].item.id,
						num: myorders[i].num,
						openid: getApp().globalData.openid
					}
					tmporders.push(tmporder)
				}
				// console.log(tmporders)
				
				const res = await this.$myRequest({
					url: '/myorder/add',
					data: {
						myOrders: tmporders,
						openid: getApp().globalData.openid
					},
					method : "POST"
				})
				
				Promise.all([res]).then(() => {
					uni.showToast({
						title: `下单成功`,
						icon: 'none'
					})
					this.getOrderDatas()
				})
			},
			tabClick: function(index) {
				// console.log(index)
				this.activeIndex = index
			},
			async getOrderDatas() {
				const res = await this.$myRequest({
					url: '/myorder?openid=' + getApp().globalData.openid
				})
				console.log(res.data.data)
				this.orders = res.data.data
				console.log(this.orders)
				for(var i = 0; i < this.orders.length; i++) {
					var totalCost = 0;
					for(var j = 0; j < this.orders[i].myorders.length; j++) {
						var myorder = this.orders[i].myorders[j]
						totalCost += myorder.item.price * myorder.num
					}
					this.$set(this.orders[i], 'totalCost', totalCost)
				}
			}
			
		},
		onLoad: function(option) {
			// console.log(option.index)
			this.tabClick(option.index)
			this.getOrderDatas()
		}
	}
</script>

<style lang="scss">
	uni-search-bar {
		z-index: 2;
		position: fixed;
		top: -5px;
		left: 5px;
		width: 351px;
		border-radius: 15px;
		color: rgba(136, 136, 136, 1);
		text-align: center;
	}

	.header {
		z-index: 1;
		position: fixed;
		left: 0px;
		top: 0px;
		width: 375px;
		height: 95px;
		background-color: rgba(255, 255, 255, 100);
		text-align: center;
		box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.04);

		.tabbar {
			margin-top: 130rpx;
			display: flex;

			view {
				width: 42px;
				height: 20px;
				font-size: 14px;
				text-align: center;
				font-family: PingFangSC-regular;
				margin: auto;
				color: rgba(16, 16, 16, 100);
			}

			.activeTab {
				color: rgba(0, 80, 179, 100);

				view:nth-child(2) {
					width: 16px;
					height: 3px;
					line-height: 20px;
					border-radius: 22px;
					background-color: rgba(0, 80, 179, 100);
					color: rgba(0, 80, 179, 100);
					font-size: 14px;
					text-align: center;
					font-family: Arial;
					margin: auto;
				}
			}
		}
		
	}
	.cards-style {
		position: absolute;
		top: 200rpx;
		.card-style {
			width: 360px;
			height: auto;
			border-radius: 5px;
			background-color: #ffffff;
			margin: 20rpx 20rpx 20rpx 20rpx;
			.top {
				display: flex;
				height: 19px;
				text {
					width: auto;
					line-height: 19px;
					font-size: 13px;
					text-align: left;
					font-family: PingFangSC-regular;
				}
				.label1 {
					margin-left: 14px;
					margin-top: 10px;
				}
				.label2 {
					position: absolute;
					margin-left: 300px;
					margin-top: 10px;
				}
			}
			.stripe1{
				margin-left: 11px;
				margin-top: 15px;
				background: #eee;
				width: 338px;
				height: 2px;
			}
			.middle {
				.order {
					margin-top: 24px;
					margin-left: 11px;
					image {
						margin-left: 4px;
						width: 76px;
						height: 74px;
					}
					.title{
						position: absolute;
						margin-left: 10px;
						width: auto;
						height: 24px;
						line-height: 17px;
						color: rgba(16, 16, 16, 100);
						font-size: 16px;
						text-align: left;
						font-family: PingFangSC-bold;
					}
					.description {
						position: absolute;
						margin-left: 10px;
						margin-top: 30px;
						width: auto;
						height: 16px;
						line-height: 16px;
						color: rgba(133, 133, 133, 100);
						font-size: 14px;
						text-align: center;
						font-family: SFUIText-regular;
					}
					.number {
						position: absolute;
						margin-left: 176px;
						margin-top: 30px;
						width: auto;
						height: 16px;
						line-height: 16px;
						color: rgba(133, 133, 133, 0.99);
						font-size: 14px;
						text-align: right;
						font-family: SFUIText-regular;
					}
					.price {
						position: absolute;
						margin-left: 171px;
						margin-top: 3px;
						line-height: 12px;
						color: rgba(16, 16, 16, 0.99);
						font-size: 18px;
						text-align: left;
						font-family: PingFangSC-regular;
					}
				}
			}
			.stripe2{
				position: absolute;
				margin-left: 11px;
				margin-top: 15px;
				background: #eee;
				width: 338px;
				height: 2px;
			}
			.down {
				margin-top: 30px;
				display: flex;
				.number {
					margin-left: 150px;
					line-height: 17px;
					font-size: 16px;
					text-align: left;
					font-family: PingFangSC-regular;
				}
				.cost{
					margin-left: 32px;
					line-height: 17px;
					color: rgba(16, 16, 16, 0.99);
					font-size: 18px;
					text-align: left;
					font-family: PingFangSC-regular;
				}
			}
			.payNow {
				margin-top: 15px;
				margin-left: 250px;
				width: 80px;
				height: 26px;
				line-height: 24px;
				border-radius: 22px;
				background-color: rgba(0, 80, 179, 1);
				color: rgba(255, 255, 255, 1);
				font-size: 14px;
				text-align: center;
				font-family: Arial;
				border: 1px solid rgba(0, 80, 179, 1);
			}
			.buyAgain {
				margin-top: 15px;
				margin-left: 250px;
				width: 80px;
				height: 26px;
				line-height: 24px;
				border-radius: 22px;
				text-align: center;
				border: 1px solid rgba(0, 80, 179, 100);
				color: rgba(0, 80, 179, 100);
				font-size: 13px;
				text-align: center;
				font-family: PingFangSC-regular;
			}
			.blank {
				height: 15px;
			}
		}
		margin-bottom: 100rpx;
	}
	.bottom-bar {
		z-index: 10;
		position: fixed;
		top: 1132rpx;
		width: 750rpx;
		height: 34px;
		line-height: 20px;
		background-color: rgba(255, 255, 255, 1);
		text-align: center;
		border: 1px solid rgba(255, 0, 0, 0);
		.line {
			margin-left: 242rpx;
			margin-top: 42rpx;
			width: 134px;
			height: 5px;
			border-radius: 3px;
			background-color: rgba(0, 0, 0, 1);
			text-align: center;
		}
	}
</style>
