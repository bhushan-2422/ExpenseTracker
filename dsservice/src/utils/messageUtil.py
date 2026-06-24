import re

class MessageUtil:
    def isBankSms(self, message):
        words_to_search = ['debited','spent','card','bank']
        pattern = re.compile(r'\b(?:' + '|'.join(words_to_search) + r')\b',re.IGNORECASE)
        return pattern.search(message)