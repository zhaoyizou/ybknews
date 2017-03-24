package com.visionet.core.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import com.visionet.core.util.ResourceUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * author:liusy@visionet.com.cn
 * data:16/7/14
 */
public final class SQSService {
    public static final String SQS_QUEUE_KEY = "SQS.queue.key";
    public static final String BASE_FILENAME = "base";
    public static final String SQS_ACCESS_KEY_ID = "SQS.access_key_id";
    public static final String SQS_SECRET_ACCESS_KEY = "SQS.secret_access_key";
    private static final String ACCESS_KEY_ID = "AKIAPX5UKQX7PQU3FAWA";
    private static final String SECRET_ACCESS_KEY = "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";
    private static final String DZCX_SQS_DEFAULT = "DZCX_ORDER_SERVICE";
    private static AmazonSQS sqs = null;
    private static String queueUrl = null;

    /**
     * 初始化SQS客户端
     */
    static {
        AWSCredentials credentials;
        try {
            System.out.println("SQS_QUEUE_KEY=============>>>" + ResourceUtil.getValueBykey(BASE_FILENAME, SQS_QUEUE_KEY, DZCX_SQS_DEFAULT));
            credentials = new BasicAWSCredentials(ResourceUtil.getValueBykey(BASE_FILENAME, SQS_ACCESS_KEY_ID, ACCESS_KEY_ID),
                    ResourceUtil.getValueBykey(BASE_FILENAME, SQS_SECRET_ACCESS_KEY, SECRET_ACCESS_KEY));
            // TODO: liusy 16/7/14 18:57 增加认证
            sqs = new AmazonSQSClient(credentials);
            // TODO: liusy 16/7/14 18:58  注册服务区域 中国北京
            Region cnNorth = Region.getRegion(Regions.CN_NORTH_1);
            sqs.setRegion(cnNorth);
            // TODO: liusy 16/7/14 18:58  获取队列
            GetQueueUrlResult queueUrlResult = sqs.getQueueUrl(ResourceUtil.getValueBykey(BASE_FILENAME, SQS_QUEUE_KEY, DZCX_SQS_DEFAULT));
            queueUrl = queueUrlResult.getQueueUrl();
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon SQS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered " +
                    "a serious internal problem while trying to communicate with SQS, such as not " +
                    "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
    }

    /**
     * 私有构造方法
     */
    private SQSService() {
    }

    /**
     * 放入需要发送的消息
     *
     * @param message
     */
    public static void put(String message) {
        if (StringUtils.isBlank(message)) return;
        sqs.sendMessage(new SendMessageRequest(queueUrl, message));
    }

    /**
     * 获取消息
     *
     * @return
     */
    public static List<Message> getMessages() {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        return sqs.receiveMessage(receiveMessageRequest).getMessages();
    }

    /**
     * 删除消息
     *
     * @param messageReceiptHandle
     */
    public static void deleteMessage(String messageReceiptHandle) {
        sqs.deleteMessage(new DeleteMessageRequest(queueUrl, messageReceiptHandle));
    }
}
