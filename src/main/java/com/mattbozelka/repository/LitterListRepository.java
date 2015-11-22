package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.LitterPiece;

public interface LitterListRepository {

	List<LitterPiece> getLitterList();

}