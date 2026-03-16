package cn.alex.thread.bun;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by WCY on 2021/3/14
 * 包子类
 */
@Getter
@Setter
public class Bun {
    private String skin; // 皮

    private String stuffing; // 馅

    boolean flag = false; // 包子状态, 有 true, 无 false
}
