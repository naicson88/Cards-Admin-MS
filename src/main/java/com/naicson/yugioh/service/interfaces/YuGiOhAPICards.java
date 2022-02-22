package com.naicson.yugioh.service.interfaces;

import com.naicson.yugioh.entity.CardYuGiOhAPI;

public interface YuGiOhAPICards {
	
	CardYuGiOhAPI consultCardOnYuGiOhAPI(Long cardNumber);
}
