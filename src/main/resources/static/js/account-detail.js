const slug = (url) => {
    return new URL(url).pathname.match(/[^\/]+/g)
}

const fetchGetBalance = async () => {
    const slugs = slug(window.location.href)
    const response = await fetch(`/api/v1/balance?accountNumber=${slugs.pop()}`)
    const {code, message, result} = await response.json()
    if (code !== 100) {
        throw Error(message)
    }

    return result["balance"]
}

const renderAccountBalance = async () => {
    const accountBalanceEle = document.querySelector("#account-balance")

    try {
        const balance = await fetchGetBalance()
        accountBalanceEle.innerText = `${balance}원`
    } catch (e) {
        alert(e.message)
        window.location.href = "/account"
    }
}

window.onload = async () => {
    await renderAccountBalance()
}