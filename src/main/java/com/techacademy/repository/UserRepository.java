package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.User;

// （interface）とは、インターフェースとは、クラスに含まれるメソッドの具体的な処理内容を記述せず、変数とメソッドの型のみを定義したものです。
// 継承（extends）とは、型とコードの両方を継承します。 
public interface UserRepository extends JpaRepository<User, Integer> {
}