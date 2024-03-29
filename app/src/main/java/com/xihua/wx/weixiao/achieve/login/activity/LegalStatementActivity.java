package com.xihua.wx.weixiao.achieve.login.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.utils.TextUtils;

public class LegalStatementActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    String s = "";
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    text.setText(s);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_statement);
        init();
    }
    private void init(){
        text = findViewById(R.id.text);
        s = "WE校APP提醒您：在您使用WE校APP的各项服务前，请您务必仔细阅读并透彻理解本声明。如果您使用WE校APP，您的使用行为将被视为对本声明全部内容的认可。\n" +
                "\n" +
                "知识产权声明\n" +
                "\n" +
                "WE校APP所提供的各项服务的所有权和运作权归北京三快科技有限公司（以下简称“本公司”）。除特别说明或者法律另有特别规定外，本公司是WE校网及其中所刊登全部资料、信息的知识产权的唯一所有人。除法律法规或者本平台另有规定/约定外，用户通过WE校平台发布的消息一经发布即向公众传播和共享。\n" +
                "\n" +
                "本网站所刊登的全部资料包括但不限于网站架构、程序设计、程序代码、页面图文、音频、视频等信息（包括并不限于商户描述、用户点评、照片图片、社区帖、用户简评、回应、榜单等），本站二手交易市场、公益捐赠、反馈及树洞等频道的专题内容，以及依据本网站独有的分析模型计算的商户星级、环境、服务分数、推荐套系/产品等数据信息。\n" +
                "\n" +
                "除法律特别规定或者政府明确要求外，在未取得本站书面明确许可前，任何单位或者个人不得将本网站的任何知识产权对象进行任何目的的使用，任何单位或个人不得以任何方式以任何文字对本站资料作全部和局部复制、转载、引用和链接，任何单位或者个人不得以任何方式引诱、要求本网站注册用户或者第三方复制转载本网站内容或者同意该单位或者个人复制转载本网站内容，亦不得通过技术手段抓取本网站内容。任何注册用户将在本网站注册用户名和密码提供给任何第三方用于许可其复制本站内容的，将构成对注册协议的违反，并可能导致其账户被关闭或者处罚。\n" +
                "\n" +
                "用户保证不会将已发表于本站的信息资料，以任何形式发布或授权其它网站（及媒体）使用。同时，在法律允许的范围内，WE校网保留删除站内各类不符合规定的点评信息或者其他任何信息而不通知用户的权利。\n" +
                "\n" +
                "任何违反本站知识产权声明的行为，本站保留追究其行为人法律责任的权利。\n" +
                "\n" +
                "侵权投诉须知\n" +
                "\n" +
                "1. 根据《中华人民共和国侵权责任法》第三十六条之规定，任何第三方认为用户利用WE校平台侵害其民事权益或实施侵权行为的，包括但不限于侮辱、诽谤等，被侵权人有权书面通知WE校网采取删除、屏蔽、断开链接等必要措施。\n" +
                "\n" +
                "2. 根据《信息网络传播权保护条例》之规定，任何第三方认为WE校网所涉及的作品、表演、录音录像制品，侵犯自己的信息网络传播权或者被删除、改变了自己的权利管理电子信息的，可以向WE校网提交书面通知，要求APP删除该侵权作品，或者断开链接。通知书应当包含下列内容：\n" +
                "\n" +
                "（一）权利人的姓名（名称）、联系方式和地址；\n" +
                "\n" +
                "（二）要求删除或者断开链接的侵权作品、表演、录音录像制品的名称和网络地址；\n" +
                "\n" +
                "（三）构成侵权的初步证明材料。\n" +
                "\n" +
                "权利人应当对通知书的真实性负责。\n" +
                "\n" +
                "您同时应该了解，根据《信息网络传播权保护条例》之规定，WE校网有权将您的投诉通知书转送至相关服务对象。该服务对象接到转送的通知书后，认为其提供的作品、表演、录音录像制品未侵犯他人权利的，可以向WE校网提交书面说明，要求恢复被删除的作品、表演、录音录像制品，或者恢复与被断开的作品、表演、录音录像制品的链接。WE校网接到服务对象的书面说明后，有权立即恢复被删除的作品、表演、录音录像制品，或者恢复与被断开的作品、表演、录音录像制品的链接，同时将该服务对象的书面说明转送投诉人。此种情形下，您不得再通知WE校网删除该作品、表演、录音录像制品，或者断开与该作品、表演、录音录像制品的链接。\n" +
                "\n" +
                "3. 任何第三方（包括但不限于企业、公司、单位或个人等）认为WE校网用户发布的任何信息侵犯其合法权益的，包括但不限于以上两点，在有充分法律法规及证据足以证明的情况下，可以按照本指引文件提供的联系方式进行投诉。\n" +
                "\n" +
                "4. 为了明确法律责任，方便WE校网依法依约及时作出处理，除前述指引已提及之投诉内容外，侵权投诉还应包含下述信息：\n" +
                "（1）被侵权人的证明材料，或被侵权作品的原始链接及其它证明材料。\n" +
                "（2）侵权信息或作品在WE校网上的具体链接。\n" +
                "（3）侵权投诉人的联络方式，以便WE校网相关部门能及时回复您的投诉，最好包括电子邮件地址、电话号码或手机等。\n" +
                "（4）投诉内容须包括以下声明：“本人本着诚信原则，有证据认为该对象侵害本人或他人的合法权益。本人承诺投诉全部信息真实、准确，否则自愿承担一切后果。”\n" +
                "（5）本人亲笔签字并注明日期，如代理他人投诉的，必须出具授权人签字、盖章的授权书。\n" +
                "\n" +
                "5. 投诉人可以通过下列方式通知WE校网，将投诉资料邮寄至下述地址：\n" +
                "\n" +
                "邮寄地址：四川省成都市郫县红光镇西华大学\n" +
                "邮政编码：611730\n" +
                "收件人：WE校网客服部\n" +
                "客服电话：（鉴于电话语音通讯方式的局限性，客服电话仅用于投诉人邮寄投诉信函时辅助使用，不作为独立投诉受理方式。）\n" +
                "\n" +
                "6.WE校网建议您在提起投诉之前咨询法律顾问或律师。我们提请您注意：投诉人应当对其投诉内容的准确性、客观性、合法性、完整性承担责任。如果侵权投诉不实，则用户可能承担法律责任；如果投诉通知内容不准确、不完整，则投诉人应承担因此造成的后果。\n" +
                "\n" +
                "7.投诉人须了解，WE校网仅有权利和义务在法律法规规定及平台规则约定的范围内，在网站工作人员的认知水平和能力、资格范围内对投诉作出判断和处理或答复。这一处理机制不同于也无意替代司法机关的裁断。WE校网亦无意介入投诉人与被投诉人之间的纠纷。WE校网不对您的投诉是否能够得到某种结果作出任何承诺或者保证，亦不因对您的投诉处理行为而承担任何义务或者责任。\n" +
                "\n" +
                "作弊检测\n" +
                "\n" +
                "若WE校网通过技术检测、人工抽检等手段有合理理由怀疑用户资料信息为错误、不实、失效或不完整，本网站有权暂停或终止用户的账号，并拒绝现在或将来使用本站网站网络服务的全部或部分，同时保留追索用户不当得利返还的权利。\n" +
                "）\n" ;
        handler.sendEmptyMessage(1);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;

        }
    }
}
