package com.techacademy.controller;

import java.util.Set; //Lesson18Chapter9削除

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; //Lesson18Chapter10入力チェック追加
import org.springframework.validation.annotation.Validated; //Lesson18Chapter10入力チェック追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // Lesson18Chapter7登録追加
import org.springframework.web.bind.annotation.PathVariable; // Lesson18Chapter8更新追加
import org.springframework.web.bind.annotation.PostMapping; // Lesson18Chapter7登録追加
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; //Lesson18Chapter9削除

import com.techacademy.entity.User; // Lesson18Chapter7登録追加
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("userlist", service.getUserList());
        // user/list.htmlに画面遷移
        return "user/list";
    }

    // ----- 追加:ここから -----Lesson18Chapter7登録
    /** User登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
        // User登録画面に遷移
        return "user/register";
    }

    // ----- 変更ここから -----Lesson18Chapter10入力チェック
    /** User登録処理 */
    @PostMapping("/register")
    public String postRegister(@Validated User user, BindingResult res, Model model) {
        if(res.hasErrors()) {
            // エラーあり
            return getRegister(user);
        }
        // User登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
    // ----- 追加:ここまで -----Lesson18Chapter7登録
    // ----- 変更ここまで -----Lesson18Chapter10入力チェック

    // ----- 追加:ここから -----Lesson18Chapter8更新
    /** User更新画面を表示 */
    /*@GetMapping("/update/{id}/")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("user", service.getUser(id));
        // User更新画面に遷移
        return "user/update";
    }*/

    /** User更新処理 */
    /*@PostMapping("/update/{id}/")
    public String postUser(User user) {
        // User登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }*/
    // ----- 追加:ここまで -----Lesson18Chapter8更新
    //　★ここから試し
    @GetMapping("/update/{id}/")
    public String getUser(
            @PathVariable(value = "id", required = false) Integer id,
            @ModelAttribute("user") User user,
            Model model) {
        if (id != null) {
            // 一覧画面から遷移した場合
            model.addAttribute("user", service.getUser(id));
        } else {
            // postUser() から遷移した場合
            model.addAttribute("user", user);
        }
        return "user/update";
    }

    /**
     * User更新処理
     */
    @PostMapping("/update/{id}/")
    public String postUser(@Validated User user, BindingResult res, Model model) {
        if (res.hasErrors()) {
            // バリデーションエラーがある場合
            model.addAttribute("bindingResult", res); // エラー情報をセット
            return getUser(null, user, model);        // 再描画のためgetUser()を呼び出す
        }
        return "redirect:/user/list";
    }


    // ----- 追加:ここから -----Lesson18Chapter9削除
    /** User削除処理 */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
        // Userを一括削除
        service.deleteUser(idck);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
    // ----- 追加:ここまで -----Lesson18Chapter9削除

}