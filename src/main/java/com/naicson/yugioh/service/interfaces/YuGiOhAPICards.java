package com.naicson.yugioh.service.interfaces;

import com.naicson.yugioh.dto.CardYuGiOhAPI;

public interface YuGiOhAPICards {
	
	CardYuGiOhAPI consultCardOnYuGiOhAPI(Long cardNumber);
}
