package io.github.mdjoo0810.keepgoing.api.v1

import com.ninjasquad.springmockk.MockkBean
import io.github.mdjoo0810.keepgoing.api.API_V1_PREFIX
import io.github.mdjoo0810.keepgoing.domain.account.BalanceService
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.queryParameters
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import kotlin.test.Test

@WebMvcTest
@AutoConfigureRestDocs
class BalanceApiTest(
    @Autowired private val mockMvc: MockMvc,
) {
    @MockkBean
    private lateinit var balanceService: BalanceService

    @Test
    fun `GET v1-get-balance 200 ok`() {
        every { balanceService.getBalance("0123456789") } returns BigDecimal(1000)

        mockMvc.perform(get("$API_V1_PREFIX/balance?accountNumber={accountNumber}", "0123456789"))
            .andExpect(status().isOk)
            .andDo(
                document(
                    "balance/get-balance",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("accountNumber").description("계좌번호"),
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.NUMBER),
                        fieldWithPath("message").description("응답 메시지").type(JsonFieldType.STRING),
                        fieldWithPath("result").description("응답 결과").type(JsonFieldType.OBJECT),
                        fieldWithPath("result.balance").description("계좌 잔액").type(JsonFieldType.NUMBER),
                    ),
                ),
            )
    }

    @Test
    fun `POST v1-deposit 200 ok`() {
        // given
        val accountNumber = "0123456789"
        val amount = BigDecimal(1000)

        // when
        every { balanceService.deposit(accountNumber, amount) } returns amount

        // then
        mockMvc.perform(
            post("$API_V1_PREFIX/balance/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "accountNumber": "$accountNumber",
                      "amount": $amount
                    }
                    """.trimIndent(),
                ),
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    "balance/deposit",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("accountNumber").description("계좌번호").type(JsonFieldType.STRING),
                        fieldWithPath("amount").description("입금금액").type(JsonFieldType.NUMBER),
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.NUMBER),
                        fieldWithPath("message").description("응답 메시지").type(JsonFieldType.STRING),
                        fieldWithPath("result").description("응답 결과").type(JsonFieldType.OBJECT),
                        fieldWithPath("result.balance").description("계좌 잔액").type(JsonFieldType.NUMBER),
                    ),
                ),
            )
    }

    @Test
    fun `POST v1-withdraw 200 ok`() {
        // given
        val accountNumber = "0123456789"
        val amount = BigDecimal(1000)

        // when
        every { balanceService.withdraw(accountNumber, amount) } returns amount

        // then
        mockMvc.perform(
            post("$API_V1_PREFIX/balance/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "accountNumber": "$accountNumber",
                      "amount": $amount
                    }
                    """.trimIndent(),
                ),
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    "balance/withdraw",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("accountNumber").description("계좌번호").type(JsonFieldType.STRING),
                        fieldWithPath("amount").description("출금금액").type(JsonFieldType.NUMBER),
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.NUMBER),
                        fieldWithPath("message").description("응답 메시지").type(JsonFieldType.STRING),
                        fieldWithPath("result").description("응답 결과").type(JsonFieldType.OBJECT),
                        fieldWithPath("result.balance").description("계좌 잔액").type(JsonFieldType.NUMBER),
                    ),
                ),
            )
    }
}
