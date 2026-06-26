package com.server.tdMindJelly.jelly.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * @className : JellyStartAgingReqDTO
 * @description : 숙성 시작(PATCH /jelly/{jellyId}) 요청 DTO. status는 "AGING" 고정값.
 * @date : 2026-06-26
 */
@Getter
@NoArgsConstructor
public class JellyStartAgingReqDTO {
    private String status; // "AGING"
}
