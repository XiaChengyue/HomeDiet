<template>
	<view>
		<swiper class="goods-image" indicator-dots>
			<swiper-item>
				<image :src="info.pic"></image>
			</swiper-item>
		</swiper>
		<view class="box1">
			<view class="price">
				<!-- <text>{{info.sell_price}}</text>
				<text>{{info.market_price}}</text> -->
				<text>{{info.price}}</text>
				<text>{{info.price * 2}}</text>
			</view>
			<!-- <view class="goods_name">{{info.title}}</view> -->
			<view>
				<view>{{info.name}}	{{info.des}}</view>
				<uni-number-box min=1 :value="info.num" @change="goodsNumChange"></uni-number-box>
			</view>
		</view>
		<view class="line"></view>
		<view class="title">商品详情</view>
		<view class="box2">
			<view>商品名称	{{info.name}}</view>
			<view>商品编号	0000000{{info.id}}</view>
			<view>上架时间	2022年05月27日</view>
			<view>商品类型	{{info.type}}</view>
		</view>
		<view class="title">图文详情</view>
		<view class="box3">
			<view class="tit"></view>
			<view class="content">
				<rich-text :nodes="content"></rich-text>
			</view>
		</view>
		<view class="goods_nav">
			<uni-goods-nav :fill="true" :options="settings" :buttonGroup="buttonGroup" @click="onClick"
				@buttonClick="buttonClick" />
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				info: {},
				settings: [{
					icon: 'headphones',
					text: '客服'
				}, {
					icon: 'shop',
					text: '店铺',
					// info: 2,
					infoBackgroundColor: '#007aff',
					infoColor: "red"
				}, {
					icon: 'cart',
					text: '购物车',
					// info: 2
				}],
				buttonGroup: [{
						text: '加入购物车',
						backgroundColor: '#ff0000',
						color: '#fff'
					},
					{
						text: '立即购买',
						backgroundColor: '#ffa200',
						color: '#fff'
					}
				]
			}
		},
		methods: {
			goodsNumChange: function(e) {
				// console.log(this.cards[index].num)
				this.info.num = e
			},
			async getItemData(id) {
				const res = await this.$myRequest({
					url: '/item?id=' + id
				})
				// console.log(res.data.data[0])
				this.info = res.data.data[0]
				this.$set(this.info, "num", 1)
			},
			onClick(e) {
				uni.showToast({
					title: `点击${e.content.text}`,
					icon: 'none'
				}),
				console.log("左侧按钮信息：", e)
				if(e.index == 2) {
					uni.switchTab({
						url:"/pages/cart/cart"
					})
				}
			},
			async buttonClick(e) {
				// console.log("右侧按钮信息：", e)
				if(e.index == 0) {
					var res;
					for(var i = 0; i < this.info.num; i++) {
						res = await this.$myRequest({
							url: '/cart/add',
							data: {
								itemid: this.info.id,
								openid : getApp().globalData.openid
							},
							method : "POST"
						})
					}
					// console.log("商品数量：", this.info.num)
					// console.log("加入购物车的商品信息", res)
					if(res.data.msg == "成功")
					{
						uni.showToast({
							title: `已加入购物车`,
							icon: 'none'
						})
					}
				}
			}
		},
		onLoad(options) {
			// console.log(options)
			this.getItemData(options.id)
		}
	}
</script>

<style lang="scss">
	
	.title{
		margin-top: 11px;
		margin-bottom: 18px;
		text-align: center;
		font-family: PingFangSC-bold;
		color: rgba(16, 16, 16, 1);
		font-size: 16px;
	}
	
	.goods-image {
		height: 700rpx;

		image {
			width: 100%;
			height: 100%;
		}
	}

	.box1 {
		padding: 20rpx;

		.price {
			font-size: 64rpx;
			color: $shop-color;
			line-height: 80rpx;

			text:nth-child(2) {
				color: #ccc;
				font-size: 48rpx;
				text-decoration: line-through;
				margin-left: 20rpx;
			}
		}

		view:nth-child(2) {
			display: flex;
			view:{
				font-size: 32rpx;
			}
			uni-number-box {
				margin-left: 150rpx;
			}
		}
	}

	.goods_nav {
		position: fixed;
		bottom: 0;
		width: 100%;
	}

	.line {
		height: 24rpx;
		width: 750rpx;
		background: #eee;
	}

	.box2 {
		margin-left: 13px;
		width: 352px;
		height: 132px;
		line-height: 20px;
		border-radius: 8px;
		background-color: rgba(117, 181, 215, 0.5);
		text-align: left;
		view {
			margin-left: 25px;
			line-height: 32px;
			color: rgba(16, 16, 16, 1);
			font-size: 14px;
			text-align: left;
			font-family: PingFangSC-regular;
		}
	}

	.box3 {
		padding-bottom: 100rpx;
		.tit {
			font-size: 32rpx;
			padding-left: 10rpx;
			border-bottom: 1px solid #eee;
			line-height: 70rpx;
		}

		.content {
			padding: 20rpx;
			font-size: 28rpx;
			line-height: 50rpx;
		}
	}
</style>
