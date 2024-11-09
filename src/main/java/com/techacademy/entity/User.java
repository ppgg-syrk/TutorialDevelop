package com.techacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // Lombokのアノテーションです。「getter/setter、toString、hashCode、equals」のメソッドを生成します。
@Entity //Spring JPAのアノテーションです。エンティティクラス（データベースのテーブルにマッピングするクラス）であることを示します。
@Table(name = "user") //pring JPAのアノテーションです。エンティティが対応する（紐づく）テーブル名を指定します。
public class User {

    /** 性別用の列挙型 */ ///** で始まるコメントは「Javadoc」というコメントの書き方です。
    public static enum Gender {
        男性, 女性
    }

    /** 主キー。自動生成 */
    @Id //@Id はSpring JPAのアノテーションです。主キーであることを示します。
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue はpring JPAのアノテーションです。主キーの値を一意に自動生成します。
    // application.propertiesの設定した内容で自動生成する。
    private Integer id;

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false) //@Column　はSpringJPAのアノテーションです。フィールドをマッピングするテーブルのカラム（項目）を指定します。
    // length : フィールドの桁数を指定します。nullable フィールドのNOT NULL制約を指定します。値が「true」のときはnull値を許可し、「false」のときはnull値を許可しません。デフォルト値は「true」です。
    private String name;

    /** 性別。2桁。列挙型（文字列） */
    @Column(length = 2)
    @Enumerated(EnumType.STRING) //@Enumerated はSpring JPAのアノテーションです。このフィールドの型が列挙型であることを指定します。 EnumType.STRING 属性でテーブルのカラムを文字列型に指定します。
    private Gender gender;

    /** 年齢 */
    private Integer age;

    /** メールアドレス。50桁。null許可 */
    @Column(length = 50)
    private String email;

}
