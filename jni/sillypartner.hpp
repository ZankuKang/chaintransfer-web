#pragma once 
#ifndef SILLYPARTNER_H
#define SILLYPARTNER_H

#include "partner.hpp"

class SillyPartner : public Partner
{
    public:
        SillyPartner(const std::string &host, int port);
        void silly_conversation(const char* filename);

    private:
        std::string host;
        int port;
        int inet_pton(int af, const char *src, void *dst);
};

#endif