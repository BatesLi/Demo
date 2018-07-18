package cn.dankal.basic_lib.constant;

/**
 * description: 网络相关常量
 *
 * @author vane
 * @since 2018/2/27
 */

public class NetworkConstants {


    /**
     * 401
     * <p>
     * 当前请求需要用户验证。该响应必须包含一个适用于被请求资源的 WWW-Authenticate 信息头用以询问用户信息。
     * 客户端可以重复提交一个包含恰当的 Authorization 头信息的请求。
     * 如果当前请求已经包含了 Authorization 证书，那么401响应代表着服务器验证已经拒绝了那些证书。
     * 如果401响应包含了与前一个响应相同的身份验证询问，且浏览器已经至少尝试了一次验证，那么浏览器应当向用户展示响应中包含的实体信息，因为这个实体信息中可能包含了相关诊断信息。
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 403
     * <p>
     * 服务器已经理解请求，但是拒绝执行它。
     * 与401响应不同的是，身份验证并不能提供任何帮助，而且这个请求也不应该被重复提交。
     * 如果这不是一个 HEAD 请求，而且服务器希望能够讲清楚为何请求不能被执行，那么就应该在实体内描述拒绝的原因。
     * 当然服务器也可以返回一个404响应，假如它不希望让客户端获得任何信息。
     */
    public static final int FORBIDDEN = 403;


    /**
     * 404
     * <p>
     * 请求失败，请求所希望得到的资源未被在服务器上发现。
     * 没有信息能够告诉用户这个状况到底是暂时的还是永久的。
     * 假如服务器知道情况的话，应当使用410状态码来告知旧资源因为某些内部的配置机制问题，已经永久的不可用，而且没有任何可以跳转的地址。
     * 404这个状态码被广泛应用于当服务器不想揭示到底为何请求被拒绝或者没有其他适合的响应可用的情况下。
     * 出现这个错误的最有可能的原因是服务器端没有这个页面。
     */
    public static final int NOT_FOUND = 404;

    /**
     * 408
     * <p>
     * 请求超时。客户端没有在服务器预备等待的时间内完成一个请求的发送。
     * 客户端可以随时再次提交这一请求而无需进行任何更改。
     */
    public static final int REQUEST_TIMEOUT = 408;

    /**
     * 500
     * <p>
     * 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。
     * 一般来说，这个问题都会在服务器端的源代码出现错误时出现。
     */
    public static final int INTERNAL_SERVER_ERROR = 500;

    /**
     * 502
     * <p>
     * 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。
     */
    public static final int BAD_GATEWAY = 502;

    /**
     * 503
     * <p>
     * 由于临时的服务器维护或者过载，服务器当前无法处理请求。
     * 这个状况是临时的，并且将在一段时间以后恢复。如果能够预计延迟时间，那么响应中可以包含一个 Retry-After 头用以标明这个延迟时间。
     * 如果没有给出这个 Retry-After 信息，那么客户端应当以处理500响应的方式处理它。
     */
    public static final int SERVICE_UNAVAILABLE = 503;

    /**
     * 504
     * <p>
     * 作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应。
     */
    public static final int GATEWAY_TIMEOUT = 504;

    /**
     * 412
     * <p>
     * token失效状态码
     */
    public static final int TOKEN_INVAILD = 401;
}
