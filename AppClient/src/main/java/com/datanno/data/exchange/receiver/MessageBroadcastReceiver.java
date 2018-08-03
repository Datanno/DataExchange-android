package com.datanno.data.exchange.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.EditText;

import com.datanno.data.exchange.libs.GlobalConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageBroadcastReceiver  extends BroadcastReceiver {
    private EditText mEditText;

    public MessageBroadcastReceiver(EditText editText) {
        mEditText = editText;
    }

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Bundle bundle = arg1.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                if (null==msg)
                    return;
                if (msg.getOriginatingAddress().equals(
                        GlobalConstants.SYSTEM_PHONE_NUMBER)) {
                    Pattern pattern = Pattern.compile("[0-9]{6}");
                    Matcher matcher = pattern.matcher(msg
                            .getDisplayMessageBody());
                    if (matcher.find()) {
                        String res = matcher.group().substring(0, 6);
                        mEditText.setText(res);
                    }
                }
            }
        }
    }
}
