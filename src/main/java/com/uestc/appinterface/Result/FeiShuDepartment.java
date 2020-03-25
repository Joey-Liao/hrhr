package com.uestc.appinterface.Result;

import java.io.Serializable;

public class FeiShuDepartment implements Serializable {

    private String id;
    private String name;
    private String parent_id;
    private String leader_user_id;
    private String leader_open_id;
    private boolean create_group_chat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getLeader_user_id() {
        return leader_user_id;
    }

    public void setLeader_user_id(String leader_user_id) {
        this.leader_user_id = leader_user_id;
    }

    public String getLeader_open_id() {
        return leader_open_id;
    }

    public void setLeader_open_id(String leader_open_id) {
        this.leader_open_id = leader_open_id;
    }

    public boolean isCreate_group_chat() {
        return create_group_chat;
    }

    public void setCreate_group_chat(boolean create_group_chat) {
        this.create_group_chat = create_group_chat;
    }
}
