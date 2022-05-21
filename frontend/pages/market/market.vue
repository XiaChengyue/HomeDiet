<template>
	<view class="market">
		<uni-search-bar @confirm="search" @input="input"></uni-search-bar>
		<view class="main">
			<scroll-view class="left" scroll-y>
				<view v-for="(cate, index) in cates" :class="active==index? 'active' : ''"
					:key="index" @click="clickLeft(index)">
					{{cate}}
				</view>
			</scroll-view>
			<scroll-view class="right" scroll-y>
				<view class="goodsCard" v-for="(item, index) in items" :key="index" v-if="item.type == active">
					<image mode="aspectFit" :src="item.img" @click="clickItem()"></image>
					<view>{{item.title}}</view>
					<view>提货时间：{{item.time}}</view>
					<view>
						<text>￥{{item.price}}</text>
						<text>已售{{item.sold}}件</text>
					</view>
					<button type="primary">加入购物车</button>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				cates: [
					"为你推荐", "水果区", "蔬菜区", "家禽区", "水产区", "肉食区",
					"蛋类区", "豆制品", "调味品", "米面粮油", "餐食烘焙", "营养早餐",
					"鲜奶制品", "休闲食品", "酒水饮料"
				],
				active: 0,
				items: [{
						img: "../../static/market/item1.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: "28.8",
						sold: "2700",
						type: 0
					},
					{
						img: "../../static/market/item2.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: "28.8",
						sold: "2700",
						type: 0
					},
					{
						img: "../../static/market/item3.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: "28.8",
						sold: "2700",
						type: 0
					}
				]
			}
		},
		methods: {
			clickLeft: function(index){
				this.active = index
			},
			clickItem: function(){
				uni.navigateTo({
					url : '../../pages/goods-detail/goods-detail'
				})
			}
		}
	}
</script>

<style lang=scss>

	.market {
		.main {
			display: flex;
			.left {
				border-right: 10rpx solid #eee;
				width: 208rpx;
				height: 89vh;
				background-color: #FFFFFF;
				.active {
					background-color: rgba(50, 128, 28, 1);
					border-radius: 18px;
					color: #FFFFFF;
				}
				view {
					width: 170rpx;
					height: 52rpx;
					line-height: 48rpx;
					color: #333;
					text-align: center;
					font-family: OPPOSans;
					font-weight: 400;
					font-size: 28rpx;
					margin: 20rpx 0 20rpx 10rpx;
				}
			}

			.right {
				width: 532rpx;
				height: 89vh;
				.goodsCard {
					margin: 10rpx 0 10rpx 0;
					width: 510rpx;
					height: 412rpx;
					background-color: rgb(255, 255, 255);
					border-style: none;
					border-color: unset;
					box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 6px 0px;
					/* color: rgb(255, 255, 255); */
					border-radius: 8px 12px 12px;

					image {
						overflow: hidden;
						border-color: rgb(187, 187, 187);
						border-width: 0px;
						border-style: solid;
						border-radius: 8rpx;
						box-shadow: none;
						width: 486rpx;
						height: 237rpx;
						margin-left: 10rpx;
						margin-top: 10rpx;
					}

					view:nth-child(2) {
						font-family: PingFangSC;
						font-weight: 700;
						font-size: 14px;
						color: rgb(16, 16, 16);
						font-style: normal;
						letter-spacing: 0px;
						line-height: 20px;
						text-decoration: none;
						margin-left: 10rpx;
					}

					view:nth-child(3) {
						font-family: PingFangSC;
						font-weight: 400;
						font-size: 13px;
						color: rgb(16, 16, 16);
						font-style: normal;
						letter-spacing: 0px;
						line-height: 18px;
						text-decoration: none;
						margin: 10rpx;
					}

					view:nth-child(4) {
						text:nth-child(1) {
							font-family: PingFangSC;
							font-weight: 700;
							font-size: 16px;
							color: rgba(50, 128, 28, 1);
							font-style: normal;
							letter-spacing: 0px;
							line-height: 22px;
							text-decoration: none;
							margin: 0 10rpx 0 10rpx;
						}

						text:nth-child(2) {
							font-family: PingFangSC;
							font-weight: 400;
							font-size: 14px;
							color: rgba(0, 0, 0, 0.54);
							font-style: normal;
							letter-spacing: 0px;
							line-height: 20px;
							text-decoration: none;
						}

					}

					button {
						top: -50rpx;
						left: 160rpx;
						width: 148rpx;
						height: 44rpx;
						background-color: rgb(0, 80, 179);
						border-style: none;
						border-color: unset;
						color: rgb(250, 250, 250);
						border-radius: 64rpx;
						font-size: 24rpx;
						text-align: center;
						font-weight: normal;
						font-style: normal;
						white-space: pre;
						justify-content: center;
						display: flex;
						align-items: center;
					}
				}
			}
		}
	}
</style>
