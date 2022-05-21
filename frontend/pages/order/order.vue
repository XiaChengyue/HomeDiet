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
			<view v-for="(card, index) in cards" :key="index"
				v-if="activeType=='全部' || card.type==activeType" class="card-style">
				<view class="top">
					<text class="label1">{{card.pickupTime}}</text>
					<text class="label2">{{card.type}}</text>
				</view>
				<view class="stripe1"></view>
				<view class="middle">
					<image :src="card.imagePath"></image>
					<text class="title">{{card.title}}</text>
					<text class="pickupTime">{{card.pickupTime}}提货</text>
					<text class="number">x {{card.number}}</text>
				</view>
				<view class="stripe2"></view>
				<view class="down">
					<text class="number">共{{card.number}}件 应付总额： </text>
					<text class="price">￥{{card.cost}}</text>
				</view>
				<view :class="card.type=='待付款' ? 'payNow' : 'buyAgain'">
					{{card.type=='待付款' ? '立即付款' : '再次购买'}}
				</view>
			</view>
		</view>
		<view class="bottom-bar">
			<view class="line"></view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				activeIndex: 0,
				activeType: "全部",
				tabNames: [
					"全部",
					"待付款",
					"待提货",
					"已提货"
				],
				cards: [{
						tradeTime: "2021-05-01",
						imagePath: "../../static/market/item2.png",
						title: "商品1",
						pickupTime: "2021-05-20",
						number: 2,
						cost: 169.00,
						type: "已提货"
					},
					{
						tradeTime: "2021-05-01",
						imagePath: "../../static/market/item2.png",
						title: "商品2",
						pickupTime: "2021-05-20",
						number: 2,
						cost: 169.00,
						type: "已提货"
					},
					{
						tradeTime: "2021-05-01",
						imagePath: "../../static/market/item2.png",
						title: "商品3",
						pickupTime: "2021-05-20",
						number: 2,
						cost: 169.00,
						type: "待付款"
					},
					{
						tradeTime: "2021-05-01",
						imagePath: "../../static/market/item2.png",
						title: "商品4",
						pickupTime: "2021-05-20",
						number: 2,
						cost: 169.00,
						type: "待付款"
					}
				]
			}
		},
		methods: {
			tabClick: function(index) {
				// console.log(index)
				this.activeIndex = index
				switch(index)
				{
					case 0:
						this.activeType = '全部'
						break
					case 1:
						this.activeType = '待付款'
						break
					case 2:
						this.activeType = '待提货'
						break
					case 3:
						this.activeType = '已提货'
						break
					default:
						this.activeType = '全部'
				}
			}
		},
		onLoad: function(option) {
			// console.log(option.index)
			this.tabClick(option.index)
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
			height: 210px;
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
					margin-left: 206px;
					margin-top: 10px;
				}
			}
			.stripe1{
				position: absolute;
				margin-left: 11px;
				margin-top: 15px;
				background: #eee;
				width: 338px;
				height: 2px;
			}
			.middle {
				margin-top: 24px;
				margin-left: 11px;
				image {
					position: absolute;
					margin-left: 4px;
					margin-top: 5px;
					width: 76px;
					height: 74px;
				}
				.title{
					position: absolute;
					margin-left: 91px;
					width: 249px;
					height: 24px;
					line-height: 17px;
					color: rgba(16, 16, 16, 100);
					font-size: 16px;
					text-align: left;
					font-family: PingFangSC-bold;
				}
				.pickupTime {
					position: absolute;
					margin-left: 90px;
					margin-top: 50px;
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
					margin-left: 316px;
					margin-top: 55px;
					width: auto;
					height: 16px;
					line-height: 16px;
					color: rgba(16, 16, 16, 99);
					font-size: 14px;
					text-align: right;
					font-family: SFUIText-regular;
				}
			}
			.stripe2{
				position: absolute;
				margin-left: 11px;
				margin-top: 85px;
				background: #eee;
				width: 338px;
				height: 2px;
			}
			.down {
				margin-top: 120px;
				display: flex;
				.number {
					margin-left: 150px;
					line-height: 17px;
					font-size: 16px;
					text-align: left;
					font-family: PingFangSC-regular;
				}
				.price{
					margin-left: 20px;
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
