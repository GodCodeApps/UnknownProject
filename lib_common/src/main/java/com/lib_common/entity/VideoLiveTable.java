package com.lib_common.entity;

import java.util.List;

/**
 * Peng YanMing on 2018\8\6 0006
 */
public class VideoLiveTable {

    /**
     * channels : [{"id":1,"name":"推荐","client_log_name":"subv_xg_live_recommend"},{"id":3,"name":"游戏","client_log_name":"subv_xg_live_game"},{"id":15,"name":"音乐","client_log_name":"subv_xg_live_music"},{"id":14,"name":"乡野","client_log_name":"subv_xg_live_countryside"}]
     * default_channel : 0
     * base_resp : {"status_code":0,"status_message":"success"}
     */

    private int default_channel;
    private BaseRespBean base_resp;
    private List<ChannelsBean> channels;

    public int getDefault_channel() {
        return default_channel;
    }

    public void setDefault_channel(int default_channel) {
        this.default_channel = default_channel;
    }

    public BaseRespBean getBase_resp() {
        return base_resp;
    }

    public void setBase_resp(BaseRespBean base_resp) {
        this.base_resp = base_resp;
    }

    public List<ChannelsBean> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelsBean> channels) {
        this.channels = channels;
    }

    public static class BaseRespBean {
        /**
         * status_code : 0
         * status_message : success
         */

        private int status_code;
        private String status_message;

        public int getStatus_code() {
            return status_code;
        }

        public void setStatus_code(int status_code) {
            this.status_code = status_code;
        }

        public String getStatus_message() {
            return status_message;
        }

        public void setStatus_message(String status_message) {
            this.status_message = status_message;
        }
    }

    public static class ChannelsBean {
        /**
         * id : 1
         * name : 推荐
         * client_log_name : subv_xg_live_recommend
         */

        private int id;
        private String name;
        private String client_log_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClient_log_name() {
            return client_log_name;
        }

        public void setClient_log_name(String client_log_name) {
            this.client_log_name = client_log_name;
        }
    }
}
