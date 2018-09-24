package naitokikaku.sscoordinator.presentation.controller.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.password.RawPassword;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
class PasswordForm implements Serializable {
    @Valid
    RawPassword currentPassword = new RawPassword();
    @Valid
    RawPassword newPassword = new RawPassword();
    @Valid
    RawPassword retypedNewPassword = new RawPassword();

    Boolean noTypoPassword;

    @AssertTrue(message = "入力されたパスワードが一致しません。")
    public boolean isNoTypoPassword() {
        return newPassword.same(retypedNewPassword);
    }
}
