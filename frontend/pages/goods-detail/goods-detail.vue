<template>
	<view>
		<swiper class="goods-image" indicator-dots>
			<swiper-item v-for="(item, index) in swipers" :key="index">
				<image src="item.src"></image>
			</swiper-item>
		</swiper>
		<view class="box1">
			<view class="price">
				<!-- <text>{{info.sell_price}}</text>
				<text>{{info.market_price}}</text> -->
				<text>114</text>
				<text>514</text>
			</view>
			<!-- <view class="goods_name">{{info.title}}</view> -->
			<view class="goods_name">AMD</view>
		</view>
		<view class="line"></view>
		<view class="title">商品详情</view>
		<view class="box2">
			<view>商品名称	{{info.goods_name}}</view>
			<view>商品编号	{{info.goods_id}}</view>
			<view>上架时间	{{info.uploaded_time}}</view>
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
			<uni-goods-nav :fill="true" :options="options" :buttonGroup="buttonGroup" @click="onClick"
				@buttonClick="buttonClick" />
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: 0,
				swipers: [],
				info: {},
				content: '',
				options: [{
					icon: 'headphones',
					text: '客服'
				}, {
					icon: 'shop',
					text: '店铺',
					info: 2,
					infoBackgroundColor: '#007aff',
					infoColor: "red"
				}, {
					icon: 'cart',
					text: '购物车',
					info: 2
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
			async getSwipers() {
				const res = await this.$myRequest({
					url: '/api/getthumimages/' + this.id
				})
				this.swipers = res.data.message
			},
			async getDetailInfo() {
				const res = await this.$myRequest({
					url: '/api/goods/getinfo/' + this.id
				})
				this.info = res.data.message
			},
			async asgetDetailContent() {
				const res = await this.$myRequest({
					url: '/api/goods/getdesc/' + this.id
				})
				this.content = res.data.message[0].content
			},
			onClick(e) {
				uni.showToast({
					title: `点击${e.content.text}`,
					icon: 'none'
				})
			},
			buttonClick(e) {
				console.log(e)
				this.options[2].info++
			}
		},
		onLoad(options) {
			// this.id = options.id
			// this.getSwipers()
			// this.getDetailInfo()
			// this.asgetDetailContent()
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
			font-size: 35rpx;
			color: $shop-color;
			line-height: 80rpx;

			text:nth-child(2) {
				color: #ccc;
				font-size: 28rpx;
				text-decoration: line-through;
				margin-left: 20rpx;
			}
		}

		.goods_name {
			font-size: 32rpx;
			line-height: 60rpx;
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
