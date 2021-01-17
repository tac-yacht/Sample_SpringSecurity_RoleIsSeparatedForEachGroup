package com.example.security;

import static com.example.security.ServiceNames.*;

public class GroupPermissionExpressionBuilder {
	public static String hasGroupAuthority(String pathVariableName, String authority) {
		//accsess内の短絡評価で先に認証済みでないと、principal取ろうとすると死ぬ。特段ログインに回してはくれない
		//使用先メソッド側でOptionalにしても受け取れるけど、認証済みでないと落ちる。
		//今回は独自実装のhasAuthorityの引数に使うので、先に認証評価をする。
		return "isAuthenticated() and @" + GROUP_PERMISSION + ".hasAuthority(principal, #"
				+ pathVariableName + ", '" + authority + "')";
	}
}
