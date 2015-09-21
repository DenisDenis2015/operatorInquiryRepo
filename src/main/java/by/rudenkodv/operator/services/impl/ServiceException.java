package by.rudenkodv.operator.services.impl;

/**
 * ���������� ������ ����
 */
public class ServiceException extends RuntimeException {
	/**
     * ���������� ������ ����
     *
     * @param message ����� ����������� ������� ��� ������� ���� ������
     * @param cause   ������� (����������)
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}