package com.xihua.wx.weixiao.achieve.discuss.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.discuss.DiscussActivity;
import com.xihua.wx.weixiao.achieve.discuss.view.RecyclerItemView;
import com.xihua.wx.weixiao.achieve.main.info.activity.MyInfoActivity;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.query.ChatQuery;
import com.xihua.wx.weixiao.utils.DateUtils;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.RecyclerUtils;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.ChatAllResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/16 0016.
 * item_recycler.xml 的适配器
 */

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleHolder>
        implements RecyclerItemView.onSlidingButtonListener{

    private Context context;

    private List<ChatAllResponse> list = new ArrayList<>();

    private onSlidingViewClickListener onSvcl;

    private RecyclerItemView recyclers;

    public RecyclerViewAdapter(Context context ) {
        this.context = context;
    }

    public RecyclerViewAdapter(Context context,
                               List<ChatAllResponse> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleHolder holder, final int position) {
        final ChatAllResponse response = list.get(position);
        if (null == response||null==response.getUser()){
            holder.re_layout.setVisibility(View.INVISIBLE);
            return;

        }
        VolleyUtils.loadImage(context, holder.image,response.getUser().getUserImg());
        holder.title.setText(response.getUser().getUserName());
        holder.content.setText(response.getChatContent());
        holder.time.setText(DateUtils.parseDate(response.getChatCreateTime()));
        holder.layout_left.getLayoutParams().width = RecyclerUtils.getScreenWidth(context);
        holder.layout_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DiscussActivity.class);
                intent.putExtra("send_id",response.getUser().getUserId());
                context.startActivity(intent);
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    //获得布局下标（点的哪一个）
                    int subscript = holder.getLayoutPosition();
                    onSvcl.onItemClick(view, subscript);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(response.getUser().getUserId());
                int subscript = holder.getLayoutPosition();
                onSvcl.onDeleteBtnCilck(view,subscript);
            }
        });
    }

    private void delete(int sendid) {
        final Gson gson =new Gson();
        String id = SpUtil.getString(context, "userid", "-1");
        ChatQuery chatQuery = new ChatQuery();
        chatQuery.setSendId(sendid);
        chatQuery.setReceiveId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/chat/delete", gson.toJson(chatQuery),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        recyclers = (RecyclerItemView) view;
    }

    @Override
    public void onDownOrMove(RecyclerItemView recycler) {
        if(menuIsOpen()){
            if(recyclers != recycler){
                closeMenu();
            }
        }
    }

    class SimpleHolder extends  RecyclerView.ViewHolder {

        public CircleNetworkImageImage image;
        public TextView title;
        public TextView content;
        public TextView time;
        public TextView delete;
        public LinearLayout layout_left;
        private RelativeLayout re_layout;
        public SimpleHolder(View view) {
            super(view);

            image =  view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);
            time = (TextView) view.findViewById(R.id.time);
            delete = (TextView) view.findViewById(R.id.delete);
            layout_left = (LinearLayout) view.findViewById(R.id.layout_left);
            re_layout = view.findViewById(R.id.re_layout);
            ((RecyclerItemView)view).setSlidingButtonListener(RecyclerViewAdapter.this);
        }
    }

    //删除数据
    public void removeData(int position){
        list.remove(position);
        notifyDataSetChanged();
        notifyItemRemoved(position);

    }

    //关闭菜单
    public void closeMenu() {
        recyclers.closeMenu();
        recyclers = null;

    }

    // 判断是否有菜单打开
    public Boolean menuIsOpen() {
        if(recyclers != null){
            return true;
        }
        return false;
    }

    //设置在滑动侦听器上
    public void setOnSlidListener(onSlidingViewClickListener listener) {
        onSvcl = listener;
    }

    // 在滑动视图上单击侦听器
    public interface onSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
    }

}
