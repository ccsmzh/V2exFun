
package org.gino.v2exfun.data.serialize.model;

import android.text.TextUtils;

import org.gino.v2exfun.constant.ComConst;

import java.io.Serializable;

public class Member implements Serializable {
    private String avatar_large;
    private String avatar_mini;
    private String avatar_normal;
    private Number id;
    private String tagline;
    private String username;

    public String getAvatar_large() {
        return formatFullImageUrl(this.avatar_large);
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public String getAvatar_mini() {
        return formatFullImageUrl(this.avatar_mini);
    }

    public void setAvatar_mini(String avatar_mini) {
        this.avatar_mini = avatar_mini;
    }

    public String getAvatar_normal() {
        return formatFullImageUrl(this.avatar_normal);
    }

    public void setAvatar_normal(String avatar_normal) {
        this.avatar_normal = avatar_normal;
    }

    public Number getId() {
        return this.id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getTagline() {
        return this.tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String formatFullImageUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        if (url.indexOf("http") == -1) {
            return ComConst.HTTP_IMAGE_BASE_URL + url;
        } else {
            return url;
        }
    }
}
