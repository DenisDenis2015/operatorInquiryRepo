package by.rudenkodv.operator.dao.impl;

/**
 * ���������� ��� ����
 */
public class DaoException extends RuntimeException{
	
	 /**
     * ���������� ��� ����
     *
     * @param message ����� ����������� ������� ��� ������� ���� ������
     * @param cause   ������� (����������)
     */
	
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
