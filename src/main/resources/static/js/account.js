const fetchGetAccounts = async () => {
    const response = await fetch("/api/v1/accounts")
    const {code, message, result} = await response.json()
    if (code !== 100) {
        throw Error(message)
    }

    return result["accounts"]
}

const renderAccountList = async () => {
    const accountListEle = document.querySelector("#account-list")

    try {
        const accounts = await fetchGetAccounts()

        accountListEle.innerHTML = ''
        if (!accounts || accounts.length === 0) {
            const emptyItem = document.createElement("li")
            emptyItem.innerText = "등록된 계좌가 없습니다."
            accountListEle.appendChild(emptyItem)
        } else {
            accounts.forEach(acc => {
                const item = document.createElement("li")
                const itemLink = document.createElement("a")
                item.appendChild(itemLink)
                itemLink.innerText = acc.number
                itemLink.href = `/account/${acc.number}`
                accountListEle.append(item)
            })
        }
    } catch (e) {
        alert(e.message)
    }
}

const fetchRegisterAccount = async (accountNumber) => {
    const response = await fetch("/api/v1/accounts", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            accountNumber
        }),
    })

    const {code, message, result} = await response.json()
    if (code !== 100) {
        throw Error(message)
    }
}

const registerAccount = async () => {
    const accountInput = document.querySelector("#add-account-inp")
    if (!accountInput.value) {
        alert("등록할 계좌번호를 입력해주세요.")
        return
    }

    try {
        await fetchRegisterAccount(accountInput.value)
        await renderAccountList()
    } catch (e) {
        alert(e.message)
    }
}

window.onload = async () => {
    await renderAccountList()
}