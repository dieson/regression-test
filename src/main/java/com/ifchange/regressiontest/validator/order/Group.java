package com.ifchange.regressiontest.validator.order;

import javax.validation.GroupSequence;

/**
 * @ClassName: Group
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 10:41 2019/4/3
 */
@GroupSequence({Create.class, Update.class})
public interface Group {
}
