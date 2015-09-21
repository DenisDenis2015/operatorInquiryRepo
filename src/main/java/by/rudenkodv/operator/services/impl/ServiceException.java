package by.rudenkodv.operator.services.impl;

/**
 * Исключение бизнес слоя
 */
public class ServiceException extends RuntimeException {
	/**
     * Исключение бизнес слоя
     *
     * @param message текст описывающий условия при которых была ошибка
     * @param cause   причина (исключение)
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}