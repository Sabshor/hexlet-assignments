<form action="/users" method="post">
    <div>
        <label>
            Имя
            <input type="text" required name="firstName" />
        </label>
    </div>
    <div>
        <label>
            фамилия
            <input type="text" name="lastName" />
        </label>
    </div>
    <div>
        <label>
            Email
            <input type="email" required name="email" />
        </label>
    </div>
    <div>
        <label>
            Пароль
            <input type="password" required name="password" />
        </label>
    </div>
    <div>
        <label>
            Подтверждение пароля
            <input type="password" required name="passwordConfirmation" />
        </label>
    </div>
    <input type="submit" value="Зарегистрировать" />
</form>

