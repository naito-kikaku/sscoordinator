package naitokikaku.sscoordinator.presentation.controller.signup;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.password.RawPassword;
import naitokikaku.sscoordinator.domain.model.fundamentals.email.EmailAddress;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
class SignUpForm implements Serializable {
    @Valid
    AccountName accountName = new AccountName();
    @Valid
    EmailAddress emailAddress = new EmailAddress();
    @Valid
    RawPassword password = new RawPassword();
    @Valid
    RawPassword retypedPassword = new RawPassword();

    Boolean noTypoPassword; // DirectFieldAccessの設定をしてるので、@AssertTrueに対応するフィールドが必要になってしまうものらしい
    @AssertTrue(message = "入力されたパスワードが一致しません。")
    public boolean isNoTypoPassword() {
        return password.same(retypedPassword);
    }
}
