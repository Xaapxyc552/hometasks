<#import "/spring.ftl" as spring/>
<#macro headerMacro>
    <table border="3">
        <tr>
            <td>
                <form action="/locale/" method="get">
                    <input type="hidden" id="lang" name="lang" value="en-GB">
                    <input type="submit" value="English">
                </form>
                <form action="/locale/" method="get">
                    <input type="hidden" id="lang" name="lang" value="uk-UA">
                    <input type="submit" value="Українська">
                </form>
            </td>
        </tr>
        <tr>
            <td> <#if (userRole == "ROLE_ANONYMOUS")>
                    <a href="/login"><@spring.message "common.head.login"/></a>
                    <a href="/signup"><@spring.message "common.head.sign_up"/></a>
                <#else>
                    <@spring.message "common.you_login_as"/> ${username}
                    <#if userRole == "ROLE_USER">
                        <a href="/user/personal-account/1"><@spring.message "common.personal_account"/></a><br>
                    <#else>
                        <a href="/manager/tours-operations/1"><@spring.message "common.manager_page"/></a><br>
                    </#if>
                    <#if userRole == "ROLE_ADMIN">
                        <a href="/admin/new-tour"><@spring.message "common.new_tour"/></a><br>
                    </#if>
                    <a href="/logout"><@spring.message "common.logout"/></a>
                </#if>
            </td>
        </tr>

        <tr>
            <th>
                <@spring.message "common.navigation.header"/>
            </th>
        </tr>
        <tr>
            <td><a href="/tours/list/1"><@spring.message "common.navigation.tours"/></a></td>
        </tr>
    </table>
</#macro>

<#macro pagingMacro pagesSequence currentPage href>
    <table>
        <tr>
            <th>
                <@spring.message "page.description"/>
            </th>
        </tr>
        <tr>
            <#list pagesSequence as pageNumber>
                <td>
                    <#if pageNumber!=currentPage>
                        <a href=${href+"${pageNumber}"}>${pageNumber}</a>
                    <#else>${pageNumber}
                    </#if>
                </td>
            </#list>
        </tr>
    </table>
</#macro>

<#macro showMoneyByLocale hrivnas locale dollarCourse>
    <#if locale=="uk_UA">
        ${hrivnas/100} <@spring.message "money.sign"/>
    <#else>
        ${hrivnas/dollarCourse/100} <@spring.message "money.sign"/>
    </#if>
</#macro>

