package com.example.chamcong.utils;

import com.example.chamcong.exception.data.DataNotFoundException;

import java.time.LocalDateTime;

public class CheckerUtils {
    public static void throwIfNull(Object object, String message){// throw if null
        if(object == null){
            throw new DataNotFoundException(message);
        }
    }

    public static void checkTimeInPast(LocalDateTime time, String message){// throw if time in past
        if(time.isBefore(LocalDateTime.now())){
            throw new DataNotFoundException(message);
        }
    }
    public static void throwIfTrue(Boolean invalid, String message){// throw if invalid is true
        if(invalid){
            throw new DataNotFoundException(message);
        }
    }

    public static void throwIfFalse(Boolean valid, String message){// throw if invalid is true
        if(!valid){
            throw new DataNotFoundException(message);
        }
    }
}
