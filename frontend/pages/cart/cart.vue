<template>
	<view>
		<view class="fixbar">
			<checkbox-group @change="checkAll">
				<checkbox :checked="checkedAll"></checkbox>
			</checkbox-group>
			<label>全选</label>
			<label>合计:</label>
			<label>￥{{sum}}</label>
			<button>去结算{{}}</button>
		</view>
		<scroll-view class="goods" scroll-y>
			<view class="goodsCard" v-for="(item, index) in items" :key="index">
				<checkbox-group @change="select(index)">
					<checkbox :checked="item.seleted"></checkbox>
				</checkbox-group>
				<image mode="aspectFit" :src="item.img"></image>
				<view>
					<view>{{item.title}}</view>
					<view>
						<text>￥{{item.cost}}</text>
						<uni-number-box @change="goodsNumChange($event, index)"></uni-number-box>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				checkedAll: false,
				sum: 0,
				items: [{
						img: "../../static/market/item1.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: 10,
						cost: 10,
						sold: 2700,
						seleted: false
					},
					{
						img: "../../static/market/item2.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: 20,
						cost: 20,
						sold: 2700,
						seleted: false
					},
					{
						img: "../../static/market/item3.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: 30,
						cost: 30,
						sold: 2700,
						seleted: false
					},
					{
						img: "../../static/market/item3.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: 28.8,
						cost: 28.8,
						sold: 2700,
						seleted: false
					}
				]
			}
		},
		methods: {
			goodsNumChange: function(e, index) {
				var num = e;
				// console.log(e, index)
				if(this.items[index].seleted)
					this.sum = this.sum - this.items[index].cost
				this.items[index].cost = this.items[index].price * num;
				if(this.items[index].seleted)
					this.sum = this.sum + this.items[index].cost
			},
			select: function(index) {
				if(this.items[index].seleted) { // 取消勾选
					this.sum = this.sum - this.items[index].cost;
					this.items[index].seleted = false
				}
				else { // 勾选
					this.sum = this.sum + this.items[index].cost;
					this.items[index].seleted = true
				}
			},
			checkAll: function(e) {
				// console.log("进入checkAll函数")
				if(this.checkedAll) {
					this.$set(this, 'checkedAll', false)
					this.sum = 0;
					for(var i = 0; i < this.items.length; i++) {
						this.$set(this.items[i], 'seleted', false);
					}
					// console.log("取消全选, checkedAll的值为", this.checkedAll);
				}
				else {
					this.$set(this, 'checkedAll', true)
					this.sum = 0;
					for(var i = 0; i < this.items.length; i++) {
						this.$set(this.items[i], 'seleted', true)
						this.sum = this.sum + this.items[i].cost;
					}
					// console.log("全选, checkedAll的值为", this.checkedAll);
				}
			}
		}
	}
</script>

<style lang=scss>
	.fixbar {
		display: flex;
		z-index: 1;
		position: fixed;
		top: 990rpx;
		width: 375px;
		height: 60px;
		background-color: rgb(255, 255, 255);
		border-color: rgba(0, 0, 0, 0.04);
		border-width: 1px;
		border-style: solid;

		checkbox-group {
			margin-top: 30rpx;
			margin-left: 20rpx;
		}

		label:nth-child(2) {
			margin-top: 35rpx;
			margin-left: 20rpx;
			font-family: SourceHanSansSC;
			font-weight: 400;
			font-size: 14px;
			color: rgba(144, 144, 144, 1);
			font-style: normal;
			letter-spacing: 0px;
			line-height: 20px;
			text-decoration: none;
		}

		label:nth-child(3) {
			margin-top: 35rpx;
			margin-left: 100rpx;
			width: 42px;
			height: 20px;
			color: rgba(16, 16, 16, 64);
			font-size: 14px;
			text-align: left;
			font-family: SourceHanSansSC-bold;
		}

		label:nth-child(4) {
			margin-top: 35rpx;
			margin-left: 10rpx;
			width: 57px;
			height: 22px;
			color: rgba(0, 80, 179, 100);
			font-size: 16px;
			text-align: left;
			font-family: PingFangSC-bold;
		}

		button {
			position: fixed;
			top: 1004rpx;
			left: 500rpx;
			width: 110px;
			height: 40px;
			line-height: 40px;
			border-radius: 100px;
			background-color: rgba(0, 80, 179, 1);
			color: rgba(255, 255, 255, 100);
			font-size: 14px;
			text-align: center;
			font-family: Arial;
			border: 1px solid rgba(0, 80, 179, 100);
		}
	}

	.goods {
		margin-bottom: 150rpx;
		.goodsCard {
			display: flex;
			margin: 10rpx 0 10rpx 0;
			height: 246rpx;
			background-color: rgb(255, 255, 255);
			border-style: none;
			border-color: unset;
			box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 6px 0px;
			/* color: rgb(255, 255, 255); */
			border-radius: 8px 12px 12px;
			
			checkbox-group {
				margin-top: 100rpx;
				margin-left: 22rpx;
			}
			
			image {
				margin-left:0px;
				margin-top: 22px;
				width: 140px;
				height: 84px;
				border-radius: 4px;
			}

			view {
				margin-top: 10px;
				view:nth-child(1) {
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


				view:nth-child(2) {
					display: flex;
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
				}
			}
		}
	}
</style>
