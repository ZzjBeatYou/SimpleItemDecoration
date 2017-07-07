package com.mop.activity.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mop.activity.utils.Utils;

/**
 * Created by zzj on 2017/7/7.
 * 只实现了LinearLayoutManager
 */

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    private Paint dividerPaint;

    protected final Builder builder;

    private SimpleItemDecoration(Builder builder) {
        this.builder = builder;
        if (builder.getDividerColor() != 0) {
            dividerPaint = new Paint();
            dividerPaint.setColor(Utils.getColor(builder.getDividerColor()));
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (dividerPaint == null)
            return;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
              /* 获取item数 */
            int childCount = parent.getChildCount();

            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                 /* 分割块的左边距*/
                int left = parent.getPaddingLeft() + builder.getDividerLeftPadding();

                /* 分割块的右边距*/
                int right = parent.getWidth() - parent.getPaddingRight() - builder.getDividerRightPadding();

                if (builder.isShowFirstDivider()) {
                    /* 绘制RecyclerView第一个分割块*/
                    c.drawRect(left, 0, right, builder.getDividerHeight(), dividerPaint);
                }
                /* 循环绘制每一个item底部的分割块*/
                for (int i = 0; i < childCount - 1; i++) {
                    View view = parent.getChildAt(i);
                    float top = view.getBottom();
                    float bottom = view.getBottom() + builder.getDividerHeight();
                    c.drawRect(left, top, right, bottom, dividerPaint);
                }
            } else {
                 /* 分割块的上边距*/
                int top = parent.getPaddingTop() + builder.getDividerTopPadding();

                 /* 分割块的下边距*/
                int bottom = parent.getHeight() - parent.getPaddingBottom() - builder.getDividerBottomPadding();

                if (builder.isShowFirstDivider()) {
                    /* 绘制RecyclerView第一个分割块*/
                    c.drawRect(0, top, builder.getDividerWidth(), bottom, dividerPaint);
                }

                 /* 循环绘制每一个item右边的分割块*/
                for (int i = 0; i < childCount - 1; i++) {
                    View view = parent.getChildAt(i);
                    float left = view.getRight();
                    float right = view.getRight() + builder.getDividerWidth();
                    c.drawRect(left, top, right, bottom, dividerPaint);
                }
            }
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                if (builder.isShowFirstDivider()) {
                    outRect.top = builder.getDividerHeight();
                } else {
                    outRect.bottom = builder.getDividerHeight();
                }
            } else {
                if (builder.isShowFirstDivider()) {
                    outRect.left = builder.getDividerWidth();
                } else {
                    outRect.bottom = builder.getDividerWidth();
                }
            }

        }
    }

    public static class Builder {

        private final static boolean defaultShowFirstDivider = false;

        /* 分割的高度 用于垂直线性布局 */
        private int dividerHeight;

        /* 分割的宽度 用于水平线性布局... */
        private int dividerWidth;

        /* 分割的左内边距*/
        private int dividerLeftPadding;

        /* 分割的右内边距*/
        private int dividerRightPadding;

        /* 分割的顶部内边距*/
        private int dividerTopPadding;

        /* 分割的底部内边距*/
        private int dividerBottomPadding;

        /* 分割的颜色*/
        private int dividerColor;

        /* 是否显示RecyclerView的第一个分割块*/
        private boolean showFirstDivider = defaultShowFirstDivider;

        private int getDividerHeight() {
            return dividerHeight;
        }

        public Builder setDividerHeight(int dividerHeight) {
            this.dividerHeight = dividerHeight;
            return this;
        }

        private int getDividerLeftPadding() {
            return dividerLeftPadding;
        }

        public Builder setDividerLeftPadding(int dividerLeftPadding) {
            this.dividerLeftPadding = dividerLeftPadding;
            return this;
        }

        private int getDividerRightPadding() {
            return dividerRightPadding;
        }

        public Builder setDividerRightPadding(int dividerRightPadding) {
            this.dividerRightPadding = dividerRightPadding;
            return this;
        }

        private int getDividerColor() {
            return dividerColor;
        }

        public Builder setDividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        private boolean isShowFirstDivider() {
            return showFirstDivider;
        }


        public Builder setShowFirstDivider(boolean showFirstDivider) {
            this.showFirstDivider = showFirstDivider;
            return this;
        }


        private int getDividerWidth() {
            return dividerWidth;
        }

        public Builder setDividerWidth(int dividerWidth) {
            this.dividerWidth = dividerWidth;
            return this;
        }

        private int getDividerBottomPadding() {
            return dividerBottomPadding;
        }

        public Builder setDividerBottomPadding(int dividerBottomPadding) {
            this.dividerBottomPadding = dividerBottomPadding;
            return this;
        }

        private int getDividerTopPadding() {
            return dividerTopPadding;
        }

        public Builder setDividerTopPadding(int dividerTopPadding) {
            this.dividerTopPadding = dividerTopPadding;
            return this;
        }

        public SimpleItemDecoration build() {
            return new SimpleItemDecoration(this);
        }
    }
}
